package be.helmo.api.infrastructure.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "UE25_26_USER")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    @Column(length = 100, nullable = false)
    private String lastName;

    @Column(length = 100, nullable = false)
    private String firstName;

    @Column(length = 250, nullable = false, unique = true)
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
    List<Category> categories;

    @OneToMany(mappedBy = "user")
    List<Information> informations;

    @OneToMany(mappedBy = "user")
    List<QuizzTry> quizzEssays;

    @OneToMany(mappedBy = "user")
    List<HasFriends> friends;

    protected User() {
    }

    public User(String lastName, String firstName, String email, String password, String code, Role role) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.code = code;
        this.role = role;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String nom) {
        this.lastName = nom;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String prenom) {
        this.firstName = prenom;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return this.code;
    }

    public Role getRole() {
        return this.role;
    }

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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Information> getInformations() {
        return informations;
    }

    public void setInformations(List<Information> informations) {
        this.informations = informations;
    }

    public List<QuizzTry> getQuizzEssaies() {
        return quizzEssays;
    }

    public void setQuizzEssaies(List<QuizzTry> quizzEssays) {
        this.quizzEssays = quizzEssays;
    }

    public List<HasFriends> getFriends() {
        return friends;
    }

    public void setFriends(List<HasFriends> amis) {
        this.friends = amis;
    }

    public int getId() {
        return idUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getNameRole()));
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
