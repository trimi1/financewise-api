package be.helmo.api.infrastructure.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UE25_26_QUIZZ_TRY")
public class QuizzTry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idQuizzTry;

    @ManyToOne
    @JoinColumn(name = "idQuizz", nullable = false, referencedColumnName = "idQuizz")
    private Quizz quizz;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false, referencedColumnName = "idUser")
    private User user;

    @Column(nullable = false)
    private int score;

    protected QuizzTry() {
    }

    public QuizzTry(Quizz quizz, User user, int score) {
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
