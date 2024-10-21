package be.helmo.api.infrastructure.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "UE25_26_QUIZZ")
public class Quizz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idQuizz;

    @Column(length = 250, nullable = false, unique = true)
    private String title;

    @OneToMany(mappedBy = "quizz")
    private List<RessourceQuizz> ressourcesQuizz;

    protected Quizz() {
    }

    public Quizz(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
