package nl.hu.ict.inno.streaming.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Delivery;
import com.rabbitmq.stream.Message;
import nl.hu.ict.inno.streaming.DemoEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitStreamListener {

    @Autowired
    private ObjectMapper mapper;

    @RabbitListener(queues = "someStream", containerFactory = "streams")
    public void receive(String event) throws JsonProcessingException {
        DemoEvent evt = mapper.readValue(event, DemoEvent.class);
        System.out.println("Received from Stream: " + evt.getValue());
    }

    //Zoiets als dit zou je verwachten, maar het is nog niet geheel duidelijk hoe
    //je het streaming-gedeelte van RabbitMQ over JSON objectmappers kan vertellen...
    //Je zou -gokken- dat dit hetzelfde zou werken als bij non-streaming messages,
    //zoals het bij het verzenden doet. Maar helaas!
//    @RabbitListener(queues = "someStream", containerFactory = "streams")
//    public void receive(DemoEvent evt) throws JsonProcessingException {
//        System.out.println(evt.getValue());
//    }

}
