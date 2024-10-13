package be.helmo.infrastructures.model;

import jakarta.persistence.*;

import java.security.ProtectionDomain;

@Entity
@Table(name = "UE25_26_RESSOURCEQUIZZ")
public class RessourceQuizz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRessourceQuizz;

    @Column(length = 250, nullable = false, unique = true)
    private String question;

    @Column(length = 250, nullable = false)
    private String reponse;

    @ManyToOne
    @JoinColumn(name = "idQuizz", nullable = false, referencedColumnName = "idQuizz")
    private Quizz quizz;

    protected RessourceQuizz() {
    }

    public RessourceQuizz(String question, String reponse, Quizz quizz) {
        this.question = question;
        this.reponse = reponse;
        this.quizz = quizz;
    }

    public String getQuestion() {
        return question;
    }

    public String getReponse() {
        return reponse;
    }
}
