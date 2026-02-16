package medexpress.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionType questionType;

    @Column(nullable = false, length = 500)
    private String prompt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "questionnaire_id")
    @JsonBackReference("questionnaire-question")
    private Questionnaire questionnaire;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("question-answer")
    private List<PossibleAnswer> possibleAnswers = new ArrayList<>();

    public Question() {

    }

    public Question(QuestionType questionType, String prompt) {
        this.questionType = questionType;
        this.prompt = prompt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public List<PossibleAnswer> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<PossibleAnswer> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }
}
