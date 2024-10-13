package be.helmo.infrastructures.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UE25_26_PERMISSION")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPermission;

    @Column(length = 50, nullable = false, unique = true)
    private String permission;

    @Column(length = 250)
    private String description;

    protected Permission() {
    }

    public Permission(String permission, String description) {
        this.permission = permission;
        this.description = description;
    }

    public String getPermission() {
        return this.permission;
    }

    public String getDescription() {
        return this.description;
    }
}
