package nl.hu.ict.inno.twophase;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class MessageProducer {

    @Autowired
    @Qualifier("pg1")
    private JdbcTemplate pg1;

    @Autowired
    @Qualifier("pg2")
    private JdbcTemplate pg2;
//    @Autowired
//    @Qualifier("pg2")
//    private EntityManager entities2;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String value) {
        MessageEntity newMessage = new MessageEntity(value);

        System.out.println("Persisting Send");

//        pg1.execute("insert into sent_messages(id, message) values(:id, :message);");
//        pg2.execute("insert into sent_messages(id, message) values(:id, :message);");

        System.out.println("Sending");
        rabbitTemplate.convertAndSend("twophasemessages", new MessageContract(
                String.format("Sending message %s: %s", newMessage.getId(), newMessage.getContent())));

        System.out.println("Sent");
        throw new RuntimeException("Error on Sending");
    }
}
