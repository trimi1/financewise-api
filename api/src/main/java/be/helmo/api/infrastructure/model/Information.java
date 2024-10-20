package be.helmo.api.infrastructure.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UE25_26_INFORMATION")
public class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInformation;

    @Column(length = 250, nullable = false)
    private String titre;

    @Column(length = 500, nullable = false)
    private String lien;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur", nullable = false, referencedColumnName = "idUtilisateur")
    private User user;

    protected Information() {
    }

    public Information(String titre, String lien, User user) {
        this.titre = titre;
        this.lien = lien;
        this.user = user;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLien() {
        return this.lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public User getUtilisateur() {
        return user;
    }

    public void setUtilisateur(User user) {
        this.user = user;
    }
}
