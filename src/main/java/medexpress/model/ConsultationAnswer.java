package medexpress.model;

import jakarta.persistence.*;

@Entity
@Table(name = "consultation_answers")
public class ConsultationAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultation_id", nullable = false)
    private Consultation consultation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "answer_id", nullable = false)
    private PossibleAnswer selectedAnswer;

    public ConsultationAnswer() {
    }

    public ConsultationAnswer(Consultation consultation, Question question, PossibleAnswer selectedAnswer) {
        this.consultation = consultation;
        this.question = question;
        this.selectedAnswer = selectedAnswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public PossibleAnswer getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(PossibleAnswer selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}
