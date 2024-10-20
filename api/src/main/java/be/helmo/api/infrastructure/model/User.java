package be.helmo.api.infrastructure.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "UE25_26_UTILISATEUR")
public class User implements UserDetails {

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
    private String password;

    @Column(length = 6, nullable = false, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "idRole", nullable = false, referencedColumnName = "idRole")
    private Role role;

    @OneToMany(mappedBy = "user")
    List<Depense> depenses;

    @OneToMany(mappedBy = "user")
    List<Objectif> objectifs;

    @OneToMany(mappedBy = "user")
    List<Categorie> categories;

    @OneToMany(mappedBy = "user")
    List<Information> informations;

    @OneToMany(mappedBy = "user")
    List<QuizzEssaie> quizzEssaies;

    @OneToMany(mappedBy = "user")
    List<HasAmis> amis;

    protected User() {
    }

    public User(String nom, String prenom, String email, String password, String code, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getRole()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
