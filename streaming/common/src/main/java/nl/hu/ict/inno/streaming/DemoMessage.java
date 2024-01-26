package nl.hu.ict.inno.streaming;

public class DemoMessage {
    private String value;

    public DemoMessage() {}

    public DemoMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
