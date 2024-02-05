package nl.hu.ict.inno.helloeventsourcing.events;

import nl.hu.ict.inno.helloeventsourcing.SomeEntity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.UUID;

@Entity
@DiscriminatorValue("NameTypoFixed")
public class NameTypoFixed extends SomeEntityEvent {

    public String name;

    public Long getId() {
        return this.id;
    }

    protected NameTypoFixed() {
        //for Hibernate & Friends
    }

    public NameTypoFixed(UUID entityId, String newName) {
        super(entityId);
        this.name = newName;
    }


    @Override
    public SomeEntity applyTo(SomeEntityEventHandler handler) {
        return handler.apply(this);
    }
}
