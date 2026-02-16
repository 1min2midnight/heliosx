package medexpress.exception;

public class QuestionnaireNotFoundException extends RuntimeException {
    public QuestionnaireNotFoundException(String message) {
        super(message);
    }
}
