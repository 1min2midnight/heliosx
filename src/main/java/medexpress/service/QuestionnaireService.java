package medexpress.service;

import medexpress.exception.QuestionnaireNotFoundException;
import medexpress.model.Questionnaire;
import medexpress.repository.QuestionnaireRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionnaireService {

    private final QuestionnaireRepository questionnaireRepository;

    public QuestionnaireService(QuestionnaireRepository questionnaireRepository) {
        this.questionnaireRepository = questionnaireRepository;
    }

    @Transactional(readOnly = true)
    public Questionnaire findByCode(String code) {
        return questionnaireRepository.findByCode(code)
                .orElseThrow(() -> new QuestionnaireNotFoundException(
                        "Questionnaire with code '" + code + "' not found"
                ));
    }

    @Transactional
    public Questionnaire save(Questionnaire questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }
}
