package be.helmo.infrastrucures.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "UE25_26_ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRole;

    @Column(length = 50, nullable = false, unique = true)
    private String role;

    @OneToMany(mappedBy = "role")
    private List<Utilisateur> utilisateurs;

    protected Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
