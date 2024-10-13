package be.helmo.infrastructures.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UE25_26_CATEGORIE")
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategorie;

    @Column(length = 50, nullable = false)
    private String categorie;

    private double montantMax;

    @ManyToOne
    @JoinColumn(name = "idDevise", nullable = false, referencedColumnName = "idDevise")
    private Devise devise;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur", nullable = false, referencedColumnName = "idUtilisateur")
    private Utilisateur utilisateur;

    protected Categorie() {
    }

    public Categorie(String categorie, double montantMax, Devise devise, Utilisateur utilisateur) {
        this.categorie = categorie;
        this.montantMax = montantMax;
        this.devise = devise;
        this.utilisateur = utilisateur;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public double getMontantMax() {
        return montantMax;
    }

    public void setMontantMax(double montantMax) {
        this.montantMax = montantMax;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
