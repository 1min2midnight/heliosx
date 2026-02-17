package medexpress.controller;

import jakarta.validation.Valid;
import medexpress.dto.ConsultationResultResponse;
import medexpress.dto.SubmitConsultationRequest;
import medexpress.model.Questionnaire;
import medexpress.service.ConsultationService;
import medexpress.service.QuestionnaireService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/consultation")
public class ConsultationsController {

    private final QuestionnaireService questionnaireService;
    private final ConsultationService consultationService;

    public ConsultationsController(QuestionnaireService questionnaireService, ConsultationService consultationService) {
        this.questionnaireService = questionnaireService;
        this.consultationService = consultationService;
    }

    @GetMapping(
            path = "/questionnaire/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Questionnaire> retrieveQuestionnaire(@PathVariable("code") String code) {
        Questionnaire questionnaire = questionnaireService.findByCode(code);
        return ResponseEntity.ok(questionnaire);
    }

    @PostMapping(
            path = "/submit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ConsultationResultResponse> submitConsultation(@Valid @RequestBody SubmitConsultationRequest request) {
        ConsultationResultResponse result = consultationService.submitConsultation(request);
        return ResponseEntity.ok(result);
    }
}
