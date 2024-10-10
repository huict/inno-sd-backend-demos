package nl.hu.ict.inno.mechas.assembly;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Entity
public class Mech {
    @Id
    @GeneratedValue
    private Long id;
    private int maxTonnage;
    private Status status = Status.InDesign;

    private int bodyWeight;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Part arms;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Part legs;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Part shoulders;

    public Long getId() {
        return id;
    }

    protected Mech() {
        this.maxTonnage = 100;
        this.bodyWeight = 35;
    }

    public Mech(int maxTonnage) {
        this();
        this.maxTonnage = maxTonnage;
    }

    public void addOrReplacePart(Part part) {
        if(status == Status.Built){
            throw new RuntimeException("Mech is already built");
        }
        if(part.stock() < 1){
            throw new RuntimeException("Part is out of stock");
        }

        switch (part.slot()) {
            case Shoulder -> this.shoulders = part;
            case Arms -> this.arms = part;
            case Legs -> this.legs = part;
        }
    }

    public void build(PartDeliveryService partDeliveryService){
        if (isValid()) {
            partDeliveryService.deliver(getParts());
            this.status = Status.Built;
        } else {
            throw new RuntimeException("Mech is not valid");
        }
    }

    public List<Part> getParts() {
        return Arrays.stream(new Part[]{this.shoulders, this.arms, this.legs}).filter(Objects::nonNull).toList();
    }

    public int getTotalWeight() {
        return this.bodyWeight + getParts().stream().filter(Objects::nonNull).mapToInt(Part::weight).sum();
    }

    public boolean isComplete() {
        return getParts().size() == 3;
    }

    public boolean isValid() {
        return isComplete() && getTotalWeight() < maxTonnage;
    }

    public int getBodyWeight() {
        return this.bodyWeight;
    }

    public int getMaxTonnage() {
        return maxTonnage;
    }

    public Optional<Part> getArms() {
        return Optional.ofNullable(arms);
    }

    public Optional<Part> getLegs() {
        return Optional.ofNullable(legs);
    }

    public Optional<Part> getShoulders() {
        return Optional.ofNullable(shoulders);
    }

    public Status getStatus() {
        return status;
    }
}
