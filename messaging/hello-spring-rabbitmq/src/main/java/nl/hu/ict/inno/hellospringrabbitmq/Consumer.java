package nl.hu.ict.inno.hellospringrabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues = { "demo-queue" })
    public void consume(SomeMessage message){
        System.out.printf("Ik ontving %s: %s%n", message.getUuid(), message.getContent());
    }
}
