package nl.hu.ict.inno.mechs.parts;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"model", "manufacturer"}))
public class Part {
    @GeneratedValue
    @Id
    private Long id;

    private int weight;
    private Slot slot;

    @Column(name = "model")
    private String model;

    @JoinColumn(name = "manufacturer")
    @Cascade({CascadeType.PERSIST, CascadeType.DELETE_ORPHAN})
    @ManyToOne
    private Manufacturer manufacturer;

    protected Part() {
    }

    private int stock;

    public Part(String model, int weight, Slot slot, Manufacturer manufacturer) {
        this();
        this.weight = weight;
        this.slot = slot;
        this.model = model;
        this.manufacturer = manufacturer;
    }

    public int getWeight() {
        return weight;
    }

    public Slot getSlot() {
        return this.slot;
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    @Override
    public String toString() {
        return String.format("%s-%s", this.getManufacturer().getCompany(), this.getModel());
    }

    public void update(Part updatedFields, Manufacturer updatedManufacturer) {
        this.slot = updatedFields.getSlot();
        this.model = updatedFields.getModel();
        this.manufacturer = updatedManufacturer;
        this.weight = updatedFields.getWeight();
    }

    public int getStock() {
        return stock;
    }

    public void deliverToStock(int n) {
        this.stock = this.stock + n;
    }

    public void deliverToAssembly() {
        deliverToAssembly(1);
    }

    public void deliverToAssembly(int n) {
        if (this.stock - n >= 0) {
            this.stock = this.stock - n;
        } else {
            throw new RuntimeException("Not enough stock available");
        }
    }
}
