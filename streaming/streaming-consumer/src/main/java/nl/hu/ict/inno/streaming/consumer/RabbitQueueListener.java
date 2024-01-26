package nl.hu.ict.inno.streaming.consumer;

import nl.hu.ict.inno.streaming.DemoMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitQueueListener {

    @RabbitListener(queues = {"someQueue"})
    public void receive(DemoMessage m){
        System.out.println("Received from Queue: " + m.getValue());
    }
}
