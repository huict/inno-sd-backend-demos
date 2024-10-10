package nl.hu.ict.inno.mechs.assembly;

import jakarta.persistence.*;
import nl.hu.ict.inno.mechs.parts.Part;

import java.util.*;


@Entity
public class Mech {
    @Id
    @GeneratedValue
    private Long id;
    private int maxTonnage;

    private int bodyWeight;

    @ManyToOne
    private Part arms;
    @ManyToOne
    private Part legs;
    @ManyToOne
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
        switch (part.getSlot()) {
            case Shoulder -> this.shoulders = part;
            case Arms -> this.arms = part;
            case Legs -> this.legs = part;
        }
    }

    public List<Part> getParts() {
        return Arrays.stream(new Part[]{this.shoulders, this.arms, this.legs}).filter(Objects::nonNull).toList();
    }

    public int getTotalWeight() {
        return this.bodyWeight + getParts().stream().filter(Objects::nonNull).mapToInt(Part::getWeight).sum();
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
}
