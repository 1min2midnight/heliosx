package medexpress.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/consultation")
public class ConsultationsController {
    @GetMapping(
            path = "/questionnaire/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> retrieveQuestionnaire(@PathVariable("code") String code) {
        return ResponseEntity.ok().body(code);
    }
}
