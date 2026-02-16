package medexpress;

import medexpress.model.PossibleAnswer;
import medexpress.model.Question;
import medexpress.model.QuestionType;
import medexpress.model.Questionnaire;
import medexpress.repository.QuestionnaireRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final QuestionnaireRepository questionnaireRepository;

    public DataInitializer(QuestionnaireRepository questionnaireRepository) {
        this.questionnaireRepository = questionnaireRepository;
    }

    @Override
    public void run(String... args) {
        if (questionnaireRepository.findByCode("initial_consultation").isEmpty()) {
        Questionnaire questionnaire = new Questionnaire("initial_consultation", "Initial Consultation");

        Question q1 = new Question(QuestionType.BOOLEAN, "Are you experiencing severe pain or discomfort?");
        q1.setQuestionnaire(questionnaire);
        PossibleAnswer q1a1 = new PossibleAnswer("Yes");
        q1a1.setQuestion(q1);
        PossibleAnswer q1a2 = new PossibleAnswer("No");
        q1a2.setQuestion(q1);
        q1.getPossibleAnswers().add(q1a1);
        q1.getPossibleAnswers().add(q1a2);

        Question q2 = new Question(QuestionType.BOOLEAN, "Have your symptoms persisted for more than one week?");
        q2.setQuestionnaire(questionnaire);
        PossibleAnswer q2a1 = new PossibleAnswer("Yes");
        q2a1.setQuestion(q2);
        PossibleAnswer q2a2 = new PossibleAnswer("No");
        q2a2.setQuestion(q2);
        q2.getPossibleAnswers().add(q2a1);
        q2.getPossibleAnswers().add(q2a2);

        Question q4 = new Question(QuestionType.BOOLEAN, "Are you experiencing fever, bleeding, or difficulty breathing?");
        q4.setQuestionnaire(questionnaire);
        PossibleAnswer q4a1 = new PossibleAnswer("Yes");
        q4a1.setQuestion(q4);
        PossibleAnswer q4a2 = new PossibleAnswer("No");
        q4a2.setQuestion(q4);
        q4.getPossibleAnswers().add(q4a1);
        q4.getPossibleAnswers().add(q4a2);

        Question q5 = new Question(QuestionType.BOOLEAN, "Have you tried over-the-counter treatments without relief?");
        q5.setQuestionnaire(questionnaire);
        PossibleAnswer q5a1 = new PossibleAnswer("Yes");
        q5a1.setQuestion(q5);
        PossibleAnswer q5a2 = new PossibleAnswer("No");
        q5a2.setQuestion(q5);
        q5.getPossibleAnswers().add(q5a1);
        q5.getPossibleAnswers().add(q5a2);

        questionnaire.getQuestions().add(q1);
        questionnaire.getQuestions().add(q2);
        questionnaire.getQuestions().add(q4);
        questionnaire.getQuestions().add(q5);

        questionnaireRepository.save(questionnaire);
        }
    }
}
