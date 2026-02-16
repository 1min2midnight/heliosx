package medexpress.controller;

import medexpress.model.Questionnaire;
import medexpress.service.QuestionnaireService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/consultation")
public class ConsultationsController {

    private final QuestionnaireService questionnaireService;

    public ConsultationsController(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    @GetMapping(
            path = "/questionnaire/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Questionnaire> retrieveQuestionnaire(@PathVariable("code") String code) {
        Questionnaire questionnaire = questionnaireService.findByCode(code);
        return ResponseEntity.ok(questionnaire);
    }
}
