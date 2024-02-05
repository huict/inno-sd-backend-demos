package nl.hu.ict.inno.messagingpatterns.producer.pubsub;

public class HappyNewYearEvent {
    public int year;

    public static HappyNewYearEvent of(int year) {
        HappyNewYearEvent e = new HappyNewYearEvent();
        e.year = year;
        return e;
    }
}
