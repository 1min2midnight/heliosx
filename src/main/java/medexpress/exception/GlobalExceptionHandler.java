package medexpress.exception;

import medexpress.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(QuestionnaireNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleQuestionnaireNotFound(QuestionnaireNotFoundException ex) {
        ErrorResponse error = new ErrorResponse("QUESTIONNAIRE_NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
