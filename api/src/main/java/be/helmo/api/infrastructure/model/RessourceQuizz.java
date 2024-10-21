package be.helmo.api.infrastructure.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UE25_26_RESSOURCEQUIZZ")
public class RessourceQuizz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRessourceQuizz;

    @Column(length = 250, nullable = false, unique = true)
    private String question;

    @Column(length = 250, nullable = false)
    private String answer;

    @ManyToOne
    @JoinColumn(name = "idQuizz", nullable = false, referencedColumnName = "idQuizz")
    private Quizz quizz;

    protected RessourceQuizz() {
    }

    public RessourceQuizz(String question, String answer, Quizz quizz) {
        this.question = question;
        this.answer = answer;
        this.quizz = quizz;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Quizz getQuizz() {
        return quizz;
    }
}
