package be.helmo.infrastrucures.model;

import jakarta.persistence.*;

@Entity
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
}
