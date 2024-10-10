package nl.hu.ict.inno.mechs.parts;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
public class RabbitPartsEventsPublisher implements PartsEventsPublisher {

    private RabbitTemplate rabbitmq;

    public RabbitPartsEventsPublisher(RabbitTemplate rabbitmq) {
        this.rabbitmq = rabbitmq;
    }

    private enum Event { ADDED, DELETED, UPDATED }

    private record PartEventDTO(Event e, Part part){}

    @Override
    public void partAdded(Part part) {
        rabbitmq.convertAndSend("parts-cache", new PartEventDTO(Event.ADDED, part));
    }

    @Override
    public void partDeleted(Part part) {
        rabbitmq.convertAndSend("parts-cache", new PartEventDTO(Event.DELETED, part));
    }

    @Override
    public void partUpdated(Part part) {
        rabbitmq.convertAndSend("parts-cache", new PartEventDTO(Event.UPDATED, part));
    }
}
