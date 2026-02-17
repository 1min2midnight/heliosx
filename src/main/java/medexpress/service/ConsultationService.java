package medexpress.service;

import medexpress.dto.AnswerSubmission;
import medexpress.dto.ConsultationResultResponse;
import medexpress.dto.SubmitConsultationRequest;
import medexpress.exception.InvalidConsultationException;
import medexpress.model.*;
import medexpress.repository.ConsultationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ConsultationService {

    private final QuestionnaireService questionnaireService;
    private final ConsultationRepository consultationRepository;

    public ConsultationService(QuestionnaireService questionnaireService,
                                ConsultationRepository consultationRepository) {
        this.questionnaireService = questionnaireService;
        this.consultationRepository = consultationRepository;
    }

    @Transactional
    public ConsultationResultResponse submitConsultation(SubmitConsultationRequest request) {
        Questionnaire questionnaire = questionnaireService.findByCode(request.getQuestionnaireCode());

        ValidationContext context = validateSubmission(questionnaire, request.getAnswers());

        Consultation consultation = new Consultation(questionnaire);

        List<ConsultationAnswer> consultationAnswers = buildConsultationAnswers(
                consultation,
                request.getAnswers(),
                context);
        consultation.setAnswers(consultationAnswers);

        PrescriptionDecision decision = evaluatePrescription(consultationAnswers);
        consultation.setPrescriptionRequired(decision.isPrescriptionRequired());
        consultation.setDecisionReason(decision.getReason());

        Consultation savedConsultation = consultationRepository.save(consultation);

        return new ConsultationResultResponse(
                savedConsultation.getId(),
                savedConsultation.getPrescriptionRequired(),
                savedConsultation.getDecisionReason(),
                savedConsultation.getSubmittedAt()
        );
    }

    private ValidationContext validateSubmission(Questionnaire questionnaire, List<AnswerSubmission> submissions) {
        List<Question> questions = questionnaire.getQuestions();

        if (submissions.size() != questions.size()) {
            throw new InvalidConsultationException(
                    "Expected " + questions.size() + " answers, got " + submissions.size());
        }

        Map<Long, Question> questionMap = questions.stream()
                .collect(Collectors.toMap(Question::getId, q -> q));

        Map<Long, PossibleAnswer> answerMap = questions.stream()
                .flatMap(q -> q.getPossibleAnswers().stream())
                .distinct()
                .collect(Collectors.toMap(PossibleAnswer::getId, a -> a));

        Set<Long> answeredQuestions = new HashSet<>();

        for (AnswerSubmission submission : submissions) {
            Question question = questionMap.get(submission.getQuestionId());
            if (question == null) {
                throw new InvalidConsultationException(
                        "Question ID " + submission.getQuestionId() +
                                " does not belong to questionnaire");
            }

            if (!answeredQuestions.add(submission.getQuestionId())) {
                throw new InvalidConsultationException(
                        "Duplicate answer for question ID " + submission.getQuestionId());
            }

            boolean validAnswer = question.getPossibleAnswers().stream()
                    .anyMatch(a -> a.getId().equals(submission.getAnswerId()));

            if (!validAnswer) {
                throw new InvalidConsultationException(
                        "Answer ID " + submission.getAnswerId() +
                                " is not valid for question ID " + submission.getQuestionId());
            }
        }

        return new ValidationContext(questionMap, answerMap);
    }

    private List<ConsultationAnswer> buildConsultationAnswers(
            Consultation consultation,
            List<AnswerSubmission> submissions,
            ValidationContext context) {
        return submissions.stream()
                .map(submission -> {
                    Question question = context.getQuestion(submission.getQuestionId());
                    if (question == null) {
                        throw new InvalidConsultationException("Question not found");
                    }

                    PossibleAnswer answer = context.getAnswer(submission.getAnswerId());
                    if (answer == null) {
                        throw new InvalidConsultationException("Answer not found");
                    }

                    return new ConsultationAnswer(consultation, question, answer);
                })
                .collect(Collectors.toList());
    }

    private PrescriptionDecision evaluatePrescription(List<ConsultationAnswer> answers) {
        long yesCount = answers.stream()
                .filter(answer -> "Yes".equalsIgnoreCase(answer.getSelectedAnswer().getAnswerText()))
                .count();

        if (yesCount >= 3) {
            return new PrescriptionDecision(true,
                    "Prescription recommended based on consultation answers (" + yesCount + " of " + answers.size() + " symptoms confirmed).");
        } else {
            return new PrescriptionDecision(false,
                    "Prescription not recommended. Consider OTC treatments and monitoring (" + yesCount + " of " + answers.size() + " symptoms confirmed).");
        }
    }

    private static class PrescriptionDecision {
        private final boolean prescriptionRequired;
        private final String reason;

        public PrescriptionDecision(boolean prescriptionRequired, String reason) {
            this.prescriptionRequired = prescriptionRequired;
            this.reason = reason;
        }

        public boolean isPrescriptionRequired() {
            return prescriptionRequired;
        }

        public String getReason() {
            return reason;
        }
    }

    private static class ValidationContext {
        private final Map<Long, Question> questionMap;
        private final Map<Long, PossibleAnswer> answerMap;

        public ValidationContext(Map<Long, Question> questionMap, Map<Long, PossibleAnswer> answerMap) {
            this.questionMap = questionMap;
            this.answerMap = answerMap;
        }

        public Question getQuestion(Long questionId) {
            return questionMap.get(questionId);
        }

        public PossibleAnswer getAnswer(Long answerId) {
            return answerMap.get(answerId);
        }
    }
}
