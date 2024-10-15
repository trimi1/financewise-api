package be.helmo.api.infrastructure.model;

import jakarta.persistence.*;

import java.util.List;

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

    @OneToMany(mappedBy = "utilisateur")
    List<Depense> depenses;

    @OneToMany(mappedBy = "utilisateur")
    List<Objectif> objectifs;

    @OneToMany(mappedBy = "utilisateur")
    List<Categorie> categories;

    @OneToMany(mappedBy = "utilisateur")
    List<Information> informations;

    @OneToMany(mappedBy = "utilisateur")
    List<QuizzEssaie> quizzEssaies;

    @OneToMany(mappedBy = "utilisateur")
    List<HasAmis> amis;

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

    public String getMotDePasse() {return this.motDePasse;}
    public String getCode() {return this.code;}
    public Role getRole() { return this.role;}

    public List<Depense> getDepenses() {
        return this.depenses;
    }

    public void setDepenses(List<Depense> depenses) {
        this.depenses = depenses;
    }

    public List<Objectif> getObjectifs() {
        return objectifs;
    }

    public void setObjectifs(List<Objectif> objectifs) {
        this.objectifs = objectifs;
    }

    public List<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

    public List<Information> getInformations() {
        return informations;
    }

    public void setInformations(List<Information> informations) {
        this.informations = informations;
    }

    public List<QuizzEssaie> getQuizzEssaies() {
        return quizzEssaies;
    }

    public void setQuizzEssaies(List<QuizzEssaie> quizzEssaies) {
        this.quizzEssaies = quizzEssaies;
    }

    public List<HasAmis> getAmis() {
        return amis;
    }

    public void setAmis(List<HasAmis> amis) {
        this.amis = amis;
    }
}
