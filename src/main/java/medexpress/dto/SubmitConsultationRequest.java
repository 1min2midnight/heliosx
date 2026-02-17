package medexpress.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class SubmitConsultationRequest {
    @NotBlank(message = "Questionnaire code is required")
    private String questionnaireCode;

    @NotEmpty(message = "Answers are required")
    @Valid
    private List<AnswerSubmission> answers;

    public SubmitConsultationRequest() {
    }

    public SubmitConsultationRequest(String questionnaireCode, List<AnswerSubmission> answers) {
        this.questionnaireCode = questionnaireCode;
        this.answers = answers;
    }

    public String getQuestionnaireCode() {
        return questionnaireCode;
    }

    public void setQuestionnaireCode(String questionnaireCode) {
        this.questionnaireCode = questionnaireCode;
    }

    public List<AnswerSubmission> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerSubmission> answers) {
        this.answers = answers;
    }
}
