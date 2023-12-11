package nl.hu.ict.inno.streaming.producer;

import nl.hu.ict.inno.streaming.DemoEvent;
import nl.hu.ict.inno.streaming.DemoMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;
import org.springframework.stereotype.Component;

//@Component
public class RabbitRunner implements CommandLineRunner {

    @Autowired
    private RabbitStreamTemplate streamTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(rabbitTemplate.getMessageConverter());

        rabbitTemplate.convertAndSend("someQueue", new DemoMessage("Hello world"));

        streamTemplate.convertAndSend(new DemoEvent("Well that happened")).handle((result, ex) -> {
            System.out.println("Returning?");
            if(ex != null){
                ex.printStackTrace();
            }

            return result;
        });


    }
}
