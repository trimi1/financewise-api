package be.helmo.api.infrastructure.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UE25_26_INFORMATION")
public class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInformation;

    @Column(length = 250, nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String link;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false, referencedColumnName = "idUser")
    private User user;

    protected Information() {
    }

    public Information(String title, String link, User user) {
        this.title = title;
        this.link = link;
        this.user = user;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String titre) {
        this.title = titre;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String lien) {
        this.link = lien;
    }

    public User getUtilisateur() {
        return user;
    }

    public void setUtilisateur(User user) {
        this.user = user;
    }
}
