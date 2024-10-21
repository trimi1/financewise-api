package be.helmo.api.infrastructure.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UE25_26_HAS_FRIENDS")
public class HasFriends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHasFriends;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false, referencedColumnName = "idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idfriend", nullable = false, referencedColumnName = "idUser")
    private User friend;

    @ManyToOne
    @JoinColumn(name = "idPermission", nullable = false, referencedColumnName = "idPermission")
    private Permission permission;

    protected HasFriends() {
    }

    public HasFriends(User user, User friend, Permission permission) {
        this.user = user;
        this.friend = friend;
        this.permission = permission;
    }

    public User getUtilisateur() {return user;}

    public User getFriend() {
        return this.friend;
    }

    public Permission getPermission() {
        return this.permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

}
