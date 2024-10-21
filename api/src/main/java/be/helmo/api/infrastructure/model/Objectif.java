package be.helmo.api.infrastructure.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "UE25_26_OBJECTIF")
public class Objectif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idObjectif;

    @Column(length = 250, nullable = false)
    private String name;

    @Column(nullable = false)
    private double montant;

    private LocalDateTime deadline;

    @ManyToOne
    @JoinColumn(name = "idDevise", nullable = false, referencedColumnName = "idDevise")
    private Devise devise;

    @Column(length = 500)
    private String recommendation;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false, referencedColumnName = "idUser")
    private User user;

    protected Objectif() {
    }

    public Objectif(String name, double montant, LocalDateTime deadline, Devise devise, String recommendation, User user) {
        this.name = name;
        this.montant = montant;
        this.deadline = deadline;
        this.devise = devise;
        this.recommendation = recommendation;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String nom) {
        this.name = nom;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime dateLimite) {
        this.deadline = dateLimite;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommandation) {
        this.recommendation = recommandation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
