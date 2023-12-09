package nl.hu.ict.inno.streaming;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

public class DemoEvent {
    private String value;
    private UUID id = UUID.randomUUID();
    //A -very bad- timestamp for a messaging system
    private long timestamp = System.currentTimeMillis();
    private String source = InetAddress.getLocalHost().getHostName();

    public DemoEvent() throws UnknownHostException {}

    public DemoEvent(String value) throws UnknownHostException {
        this();
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public UUID getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getSource() {
        return source;
    }
}
