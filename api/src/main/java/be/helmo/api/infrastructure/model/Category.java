package be.helmo.api.infrastructure.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UE25_26_CATEGORIE")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategorie;

    @Column(length = 50, nullable = false)
    private String name;

    private double montantMax;

    @ManyToOne
    @JoinColumn(name = "idDevise", nullable = false, referencedColumnName = "idDevise")
    private Devise devise;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false, referencedColumnName = "idUser")
    private User user;

    protected Category() {
    }

    public Category(String name, double montantMax, Devise devise, User user) {
        this.name = name;
        this.montantMax = montantMax;
        this.devise = devise;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String categorie) {
        this.name = categorie;
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

    public User getUser() {
        return user;
    }

    public void setUtilisateur(User user) {
        this.user = user;
    }

    public int getId() {
        return idCategorie;
    }
}
