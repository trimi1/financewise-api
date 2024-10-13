package be.helmo.infrastructures.model;

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

}
