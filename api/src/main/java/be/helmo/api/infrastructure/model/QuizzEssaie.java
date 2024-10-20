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
    private User user;

    @Column(nullable = false)
    private int score;

    protected QuizzEssaie() {
    }

    public QuizzEssaie(Quizz quizz, User user, int score) {
        this.quizz = quizz;
        this.user = user;
        this.score = score;
    }

    public Quizz getQuizz() {
        return quizz;
    }

    public User getUtilisateur() {
        return user;
    }

    public int getScore() {
        return score;
    }

}
