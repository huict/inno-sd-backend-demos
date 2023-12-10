package nl.hu.ict.inno.streaming.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.MessageConverter;


public class CustomJacksonConverter implements MessageConverter {
    @Override
    public Object fromMessage(Message<?> message, Class<?> targetClass) {


        ObjectMapper mapper = new ObjectMapper();
        String body = new String(message.getPayload().toString());
        try {
            return mapper.readValue(body, targetClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Message<?> toMessage(Object payload, MessageHeaders headers) {
        return null;
    }
}
