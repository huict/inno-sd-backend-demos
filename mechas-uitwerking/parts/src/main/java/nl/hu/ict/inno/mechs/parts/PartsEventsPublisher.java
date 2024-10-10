package nl.hu.ict.inno.mechs.parts;

public interface PartsEventsPublisher {
    void partAdded(Part part);
    void partDeleted(Part part);
    void partUpdated(Part part);
}
