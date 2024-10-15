package be.helmo.api.infrastructure.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "UE25_26_DEPENSE")
public class Depense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDepense;

    @Column(length = 100, nullable = false)
    private String nom;

    @Column(nullable = false)
    private double montant;

    @ManyToOne
    @JoinColumn(name = "idDevise", nullable = false, referencedColumnName = "idDevise")
    private Devise devise;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "idCategorie", referencedColumnName = "idCategorie")
    private Categorie categorie;

    @ManyToOne
    @JoinColumn(name = "idObjectif", referencedColumnName = "idObjectif")
    private Objectif objectif;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur", nullable = false, referencedColumnName = "idUtilisateur")
    private Utilisateur utilisateur;

    protected Depense() {
    }

    public Depense(String nom, double montant, Devise devise, LocalDateTime date, Categorie categorie, Objectif objectif, Utilisateur utilisateur) {
        this.nom = nom;
        this.montant = montant;
        this.devise = devise;
        this.date = date;
        this.categorie = categorie;
        this.objectif = objectif;
        this.utilisateur = utilisateur;
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

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Objectif getObjectif() {
        return objectif;
    }

    public void setObjectif(Objectif objectif) {
        this.objectif = objectif;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
