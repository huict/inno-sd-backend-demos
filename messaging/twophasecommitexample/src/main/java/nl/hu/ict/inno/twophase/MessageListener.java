package nl.hu.ict.inno.twophase;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
//
@Component
@Transactional
public class MessageListener {

    @Autowired
    @Qualifier("pg1")
    private JdbcTemplate pg1;

    @Autowired
    @Qualifier("pg2")
    private JdbcTemplate pg2;

    @RabbitListener(queues = {"twophasemessages"})
    public void receiveMessage(MessageContract m) {
        System.out.println("Received " + m.getValue());
        pg1.update("insert into received_messages(id) values(?);", new Object[]{ m.getId() });
        pg2.update("insert into received_messages(id) values(?);", new Object[]{ m.getId() });
        System.out.println("Persisted received");
//        throw new RuntimeException("Error on Receive!");
    }

//    @RabbitListener(queues = {"twophasemessages"}, ackMode = "MANUAL")
//    public void receiveMessage(
//            MessageContract m,
//            Channel channel,
//            @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
//        System.out.println("Received " + m.getValue());
//        MessageEntity message = new MessageEntity("Received " + m.getValue());
//        entities.persist(message);
//        System.out.println("Persisted received");
//        channel.basicAck(tag, false);
////        throw new RuntimeException("Error on Receive!");
//    }
}
