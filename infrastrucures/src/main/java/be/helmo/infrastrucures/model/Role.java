package be.helmo.infrastrucures.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRole;

    @Column(length = 50, nullable = false, unique = true)
    private String role;

    @OneToMany(mappedBy = "role")
    private List<Utilisateur> utilisateurs;
}
