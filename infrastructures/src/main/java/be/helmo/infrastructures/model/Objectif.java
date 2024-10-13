package be.helmo.infrastructures.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "UE25_26_OBJECTIF")
public class Objectif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idObjectif;

    @Column(length = 250, nullable = false)
    private String nom;

    @Column(nullable = false)
    private double montant;

    private LocalDateTime dateLimite;

    @ManyToOne
    @JoinColumn(name = "idDevise", nullable = false, referencedColumnName = "idDevise")
    private Devise devise;

    @Column(length = 500)
    private String recommandation;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur", nullable = false, referencedColumnName = "idUtilisateur")
    private Utilisateur utilisateur;

    protected Objectif() {
    }

    public Objectif(String nom, double montant, LocalDateTime dateLimite, Devise devise, String recommandation) {
        this.nom = nom;
        this.montant = montant;
        this.dateLimite = dateLimite;
        this.devise = devise;
        this.recommandation = recommandation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public LocalDateTime getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(LocalDateTime dateLimite) {
        this.dateLimite = dateLimite;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    public String getRecommandation() {
        return recommandation;
    }

    public void setRecommandation(String recommandation) {
        this.recommandation = recommandation;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
