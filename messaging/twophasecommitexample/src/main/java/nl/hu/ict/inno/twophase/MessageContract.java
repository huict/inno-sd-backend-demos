package nl.hu.ict.inno.twophase;

public class MessageContract {
    public String getValue() {
        return value;
    }

    private String value;

    public MessageContract() { /*voor Jackson-JSON*/ }

    public MessageContract(String value) {
        this.value = value;
    }
}
