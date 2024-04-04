package nl.hu.ict.inno.twophase;


import java.util.UUID;

public class MessageEntity {
    private UUID id;

    public String content;


    public MessageEntity(String content) {
        this.id = UUID.randomUUID();
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

}
