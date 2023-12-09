package nl.hu.ict.inno.streaming.producer;

import com.rabbitmq.stream.Environment;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;
import org.springframework.rabbit.stream.support.converter.DefaultStreamMessageConverter;
import org.springframework.rabbit.stream.support.converter.StreamMessageConverter;


@Configuration
public class RabbitConfig {

    @Bean
    public Queue stream() {
        return QueueBuilder.durable("someStream")
                .stream()
                .build();
    }

    @Bean
    public Queue normalQueue(){
        return QueueBuilder.durable("someQueue")
                .build();
    }

    @Bean
    public RabbitStreamTemplate streamTemplate(Environment env){
        var template = new RabbitStreamTemplate(env, "someStream");
        template.setMessageConverter(converter());
        return template;
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

}
