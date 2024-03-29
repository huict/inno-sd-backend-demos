package nl.hu.ict.inno.helloeventsourcing.events;

import nl.hu.ict.inno.helloeventsourcing.SomeEntity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.UUID;

@Entity
@DiscriminatorValue("ContactDataChanged")
public class ContactDataChanged extends SomeEntityEvent {

    public String email;
    public String phonenr;

    public Long getId() {
        return this.id;
    }

    protected ContactDataChanged() {
        //For Hibernate & Friends
    }

    public ContactDataChanged(UUID id, String newEmail, String newPhone) {
        super(id);
        this.email = newEmail;
        this.phonenr = newPhone;
    }

    @Override
    public SomeEntity applyTo(SomeEntityEventHandler handler) {
        return handler.apply(this);
    }
}
