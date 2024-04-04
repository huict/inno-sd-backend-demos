package nl.hu.ict.inno.twophase;


public class MessageEntity {
    private Long id;

    public String content;


    public MessageEntity(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

}
