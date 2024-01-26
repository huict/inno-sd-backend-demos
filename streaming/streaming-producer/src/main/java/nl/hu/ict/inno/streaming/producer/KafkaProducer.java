package nl.hu.ict.inno.streaming.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer implements CommandLineRunner {

    @Autowired
    private KafkaTemplate templ;

    @Override
    public void run(String... args) throws Exception {
        templ.send("someTopic", "Hello World");
    }
}
