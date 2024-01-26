package nl.hu.ict.inno.streaming.consumer;

import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KafkaTopicListener implements ConsumerSeekAware {


    @KafkaListener(id = "someOtherId", topics = "someTopic")
    public void listen(String in) {
        System.out.println(in);
    }

    //Uiteraard is het waarschijnlijk niet handig om altijd vanaf het begin te lezen, maar het
    //is wel -het- grote verschil in vergelijking tot reguliere messaging
    @Override
    public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {
        for(var assignment: assignments.keySet()){
            if(assignment.topic().equals("someTopic")){
                callback.seekToBeginning(assignment.topic(), assignment.partition());
            }
        }
    }
}
