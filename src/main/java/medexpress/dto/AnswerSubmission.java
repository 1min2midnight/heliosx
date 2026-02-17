package medexpress.dto;

import jakarta.validation.constraints.NotNull;

public class AnswerSubmission {
    @NotNull(message = "Question ID is required")
    private Long questionId;

    @NotNull(message = "Answer ID is required")
    private Long answerId;

    public AnswerSubmission() {
    }

    public AnswerSubmission(Long questionId, Long answerId) {
        this.questionId = questionId;
        this.answerId = answerId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }
}
