package be.helmo.infrastructures.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UE25_26_HAS_AMIS")
public class HasAmis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHasAmis;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur", nullable = false, referencedColumnName = "idUtilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "idAmis", nullable = false, referencedColumnName = "idUtilisateur")
    private Utilisateur amis;

    @ManyToOne
    @JoinColumn(name = "idPermission", nullable = false, referencedColumnName = "idPermission")
    private Permission permission;

    protected HasAmis() {
    }

    public HasAmis(Utilisateur utilisateur, Utilisateur amis, Permission permission) {
        this.utilisateur = utilisateur;
        this.amis = amis;
        this.permission = permission;
    }

    public Utilisateur getAmis() {
        return this.amis;
    }

    public Permission getPermission() {
        return this.permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

}
