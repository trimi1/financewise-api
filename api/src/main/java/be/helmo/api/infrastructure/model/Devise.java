package be.helmo.api.infrastructure.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UE25_26_DEVISE")
public class Devise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDevise;

    @Column(length = 50, nullable = false, unique = true)
    private String devise;

    protected Devise() {
    }

    public Devise(String devise) {
        this.devise = devise;
    }

    public int getIdDevise() {
        return this.idDevise;
    }

    public void setIdDevise(int idDevise) {
        this.idDevise = idDevise;
    }

    public String getDevise() {
        return this.devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }
}
