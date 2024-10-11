package be.helmo.infrastrucures.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UE25_26_UTILISATEUR")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUtilisateur;

    @Column(length = 100, nullable = false)
    private String nom;

    @Column(length = 100, nullable = false)
    private String prenom;

    @Column(length = 250, nullable = false)
    private String email;

    @Column(length = 255, nullable = false)
    private String motDePasse;

    @Column(length = 6, nullable = false, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "idRole", nullable = false, referencedColumnName = "idRole")
    private Role role;

    protected Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String email, String motDePasse, String code, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.code = code;
        this.role = role;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }





}
