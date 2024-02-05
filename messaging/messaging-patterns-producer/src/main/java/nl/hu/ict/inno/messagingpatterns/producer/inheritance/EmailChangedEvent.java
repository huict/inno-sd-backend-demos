package nl.hu.ict.inno.messagingpatterns.producer.inheritance;

public class EmailChangedEvent extends Event {
    public String newEmail;

    public static EmailChangedEvent of(String s) {
        EmailChangedEvent e = new EmailChangedEvent();
        e.newEmail = s;
        return e;
    }
}
