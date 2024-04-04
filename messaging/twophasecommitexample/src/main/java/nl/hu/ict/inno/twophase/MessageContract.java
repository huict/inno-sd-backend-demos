package nl.hu.ict.inno.twophase;

import java.util.UUID;

public class MessageContract {
    private UUID id;

    public String getValue() {
        return value;
    }

    private String value;

    public MessageContract() { /*voor Jackson-JSON*/ }

    public MessageContract(UUID id, String value) {
        this.id = id;
        this.value = value;
    }

    public UUID getId() {
        return id;
    }
}
