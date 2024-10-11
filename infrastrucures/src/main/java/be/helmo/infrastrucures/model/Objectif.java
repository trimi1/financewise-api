package be.helmo.infrastrucures.model;

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
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getMontant() {
        return this.montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
