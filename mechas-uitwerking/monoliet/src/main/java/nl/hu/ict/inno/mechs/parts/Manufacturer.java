package nl.hu.ict.inno.mechs.parts;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Manufacturer {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String company;

    public String getCompany() {
        return company;
    }

    protected Manufacturer() {
    }

    public Manufacturer(String company) {
        this();
        this.company = company;
    }

    public Long getId() {
        return id;
    }
}
