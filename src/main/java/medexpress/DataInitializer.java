package medexpress;

import medexpress.model.PossibleAnswer;
import medexpress.model.Question;
import medexpress.model.QuestionType;
import medexpress.model.Questionnaire;
import medexpress.repository.PossibleAnswerRepository;
import medexpress.repository.QuestionnaireRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final QuestionnaireRepository questionnaireRepository;
    private final PossibleAnswerRepository possibleAnswerRepository;

    public DataInitializer(QuestionnaireRepository questionnaireRepository,
                           PossibleAnswerRepository possibleAnswerRepository) {
        this.questionnaireRepository = questionnaireRepository;
        this.possibleAnswerRepository = possibleAnswerRepository;
    }

    @Override
    public void run(String... args) {
        if (questionnaireRepository.findByCode("initial_consultation").isEmpty()) {
            // Create and save shared Yes/No answers FIRST
            PossibleAnswer answerYes = possibleAnswerRepository.save(new PossibleAnswer("Yes"));
            PossibleAnswer answerNo = possibleAnswerRepository.save(new PossibleAnswer("No"));

            Questionnaire questionnaire = new Questionnaire("initial_consultation", "Initial Consultation");

            Question q1 = new Question(QuestionType.BOOLEAN, "Are you experiencing severe pain or discomfort?");
            q1.setQuestionnaire(questionnaire);
            q1.getPossibleAnswers().add(answerYes);
            q1.getPossibleAnswers().add(answerNo);

            Question q2 = new Question(QuestionType.BOOLEAN, "Have your symptoms persisted for more than one week?");
            q2.setQuestionnaire(questionnaire);
            q2.getPossibleAnswers().add(answerYes);
            q2.getPossibleAnswers().add(answerNo);

            Question q3 = new Question(QuestionType.BOOLEAN, "Are you experiencing fever, bleeding, or difficulty breathing?");
            q3.setQuestionnaire(questionnaire);
            q3.getPossibleAnswers().add(answerYes);
            q3.getPossibleAnswers().add(answerNo);

            Question q4 = new Question(QuestionType.BOOLEAN, "Have you tried over-the-counter treatments without relief?");
            q4.setQuestionnaire(questionnaire);
            q4.getPossibleAnswers().add(answerYes);
            q4.getPossibleAnswers().add(answerNo);

            questionnaire.getQuestions().add(q1);
            questionnaire.getQuestions().add(q2);
            questionnaire.getQuestions().add(q3);
            questionnaire.getQuestions().add(q4);

            questionnaireRepository.save(questionnaire);
        }
    }
}
