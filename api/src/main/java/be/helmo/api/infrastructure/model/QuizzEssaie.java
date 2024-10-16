package be.helmo.api.infrastructure.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UE25_26_QUIZZ_ESSAIE")
public class QuizzEssaie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idQuizzEssaie;

    @ManyToOne
    @JoinColumn(name = "idQuizz", nullable = false, referencedColumnName = "idQuizz")
    private Quizz quizz;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur", nullable = false, referencedColumnName = "idUtilisateur")
    private Utilisateur utilisateur;

    @Column(nullable = false)
    private int score;

    protected QuizzEssaie() {
    }

    public QuizzEssaie(Quizz quizz, Utilisateur utilisateur, int score) {
        this.quizz = quizz;
        this.utilisateur = utilisateur;
        this.score = score;
    }

    public Quizz getQuizz() {
        return quizz;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public int getScore() {
        return score;
    }

}
