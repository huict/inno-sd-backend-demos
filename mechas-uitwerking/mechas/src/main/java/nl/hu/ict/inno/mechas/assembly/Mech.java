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

    private int bodyWeight;

    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "arms_id")),
            @AttributeOverride(name = "name", column = @Column(name = "arms_name")),
            @AttributeOverride(name = "weight", column = @Column(name = "arms_weight")),
            @AttributeOverride(name = "slot", column = @Column(name = "arms_slot"))
    })
    private Part arms;

    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "legs_id")),
            @AttributeOverride(name = "name", column = @Column(name = "legs_name")),
            @AttributeOverride(name = "weight", column = @Column(name = "legs_weight")),
            @AttributeOverride(name = "slot", column = @Column(name = "legs_slot"))
    })
    private Part legs;

    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "shoulders_id")),
            @AttributeOverride(name = "name", column = @Column(name = "shoulders_name")),
            @AttributeOverride(name = "weight", column = @Column(name = "shoulders_weight")),
            @AttributeOverride(name = "slot", column = @Column(name = "shoulders_slot"))
    })
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
        switch (part.slot()) {
            case Shoulder -> this.shoulders = part;
            case Arms -> this.arms = part;
            case Legs -> this.legs = part;
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
}
