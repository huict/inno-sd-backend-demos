package nl.hu.ict.inno.streaming.consumer;

import nl.hu.ict.inno.streaming.DemoEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitStreamListener {

    @RabbitListener(queues = "someStream")
    public void receive(DemoEvent event){
        System.out.println(event.getValue());
    }
}
