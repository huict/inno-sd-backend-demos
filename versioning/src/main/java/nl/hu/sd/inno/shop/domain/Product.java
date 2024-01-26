package nl.hu.sd.inno.shop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) //In geval van twijfel is Sequence beter dan Identity
    private Long id;

    private int nrAvailable;

    private double price;

    private String name;

    protected Product() {
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public int getNrAvailable() {
        return nrAvailable;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void deliver(int nr) {
        this.nrAvailable += nr;
    }

    public void order(int nr) {
        this.nrAvailable -= nr;
    }
}
