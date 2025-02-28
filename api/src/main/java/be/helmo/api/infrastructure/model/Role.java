package be.helmo.api.infrastructure.model;

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
    private List<User> users;

    protected Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public String getNameRole() {
        return this.role;
    }
}
