package nl.hu.ict.inno.streaming.consumer;

import com.rabbitmq.client.Delivery;
import com.rabbitmq.stream.Message;
import nl.hu.ict.inno.streaming.DemoEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitStreamListener {

//    @RabbitListener(queues = "someStream")
//    public void receive(DemoEvent event){
//        System.out.println(event.getValue());
//    }
//
//    @RabbitListener(queues = "someStream", containerFactory = "streams")
//    public void receive(String event){
//        System.out.println(event);
//    }


    @RabbitListener(queues = "someStream", containerFactory = "streams")
    public void receive(Delivery event){
        System.out.println(event.getBody());
    }
}
