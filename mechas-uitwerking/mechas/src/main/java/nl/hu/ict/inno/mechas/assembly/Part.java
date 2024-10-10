package nl.hu.ict.inno.mechas.assembly;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Part {
    @Id
    private Long id;
    private String name;
    private int stock;
    private int weight;
    private Slot slot;

    protected Part(){}
    public Part(Long id, String name, int stock, int weight, Slot slot) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.weight = weight;
        this.slot = slot;
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public int stock() {
        return stock;
    }

    public int weight() {
        return weight;
    }

    public Slot slot() {
        return slot;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Part) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.name, that.name) &&
                this.stock == that.stock &&
                this.weight == that.weight &&
                Objects.equals(this.slot, that.slot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, stock, weight, slot);
    }

    @Override
    public String toString() {
        return "Part[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "stock=" + stock + ", " +
                "weight=" + weight + ", " +
                "slot=" + slot + ']';
    }


}
