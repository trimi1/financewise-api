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
    private String name;

    @Column(nullable = false)
    private double montant;

    @ManyToOne
    @JoinColumn(name = "idDevise", nullable = false, referencedColumnName = "idDevise")
    private Devise devise;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "idCategorie", referencedColumnName = "idCategorie")
    private Category categorie;

    @ManyToOne
    @JoinColumn(name = "idObjectif", referencedColumnName = "idObjectif")
    private Objectif objectif;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false, referencedColumnName = "idUser")
    private User user;

    protected Depense() {
    }

    public Depense(String name, double montant, Devise devise, LocalDateTime date, Category categorie, Objectif objectif, User user) {
        this.name = name;
        this.montant = montant;
        this.devise = devise;
        this.date = date;
        this.categorie = categorie;
        this.objectif = objectif;
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

    public Category getCategorie() {
        return categorie;
    }

    public void setCategorie(Category categorie) {
        this.categorie = categorie;
    }

    public Objectif getObjectif() {
        return objectif;
    }

    public void setObjectif(Objectif objectif) {
        this.objectif = objectif;
    }

    public User getUtilisateur() {
        return user;
    }

    public void setUtilisateur(User user) {
        this.user = user;
    }

    public int getId() {
        return idDepense;
    }
}
