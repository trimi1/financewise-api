package be.helmo.api.infrastructure.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UE25_26_HAS_AMIS")
public class HasAmis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHasAmis;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur", nullable = false, referencedColumnName = "idUtilisateur")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idAmis", nullable = false, referencedColumnName = "idUtilisateur")
    private User amis;

    @ManyToOne
    @JoinColumn(name = "idPermission", nullable = false, referencedColumnName = "idPermission")
    private Permission permission;

    protected HasAmis() {
    }

    public HasAmis(User user, User amis, Permission permission) {
        this.user = user;
        this.amis = amis;
        this.permission = permission;
    }

    public User getUtilisateur() {return user;}

    public User getAmis() {
        return this.amis;
    }

    public Permission getPermission() {
        return this.permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

}
