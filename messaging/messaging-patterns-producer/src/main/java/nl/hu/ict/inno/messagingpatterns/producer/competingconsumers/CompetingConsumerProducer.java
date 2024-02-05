package nl.hu.ict.inno.messagingpatterns.producer.competingconsumers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class CompetingConsumerProducer {
    private RabbitTemplate rabbit;

    public CompetingConsumerProducer(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    public void sendMessage(ProcessImageCommand message) {
        this.rabbit.convertAndSend("competingconsumers-example", message);
    }
}
