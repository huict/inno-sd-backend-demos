package nl.hu.ict.inno.streaming.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.stream.Environment;
import com.rabbitmq.stream.OffsetSpecification;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.adapter.AmqpMessageHandlerMethodFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;
import org.springframework.rabbit.stream.config.StreamRabbitListenerContainerFactory;
import org.springframework.rabbit.stream.listener.StreamListenerContainer;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;


@Configuration
public class RabbitConfig {

    @Bean
    public Queue stream() {
        return QueueBuilder.durable("someStream")
                .stream()
                .build();
    }

    @Bean
    public Queue normalQueue() {
        return QueueBuilder.durable("someQueue")
                .build();
    }

    @Bean
    public ObjectMapper mapper(){
        return new ObjectMapper();
    }

    @Bean
    public MessageConverter converter(ObjectMapper mapper) {
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public RabbitStreamTemplate streamTemplate(Environment env, MessageConverter converter) {
        var template = new RabbitStreamTemplate(env, "someStream");
        template.setMessageConverter(converter);
        return template;
    }

    @Bean
    RabbitListenerContainerFactory<StreamListenerContainer> streams(Environment env) {
        StreamRabbitListenerContainerFactory factory = new StreamRabbitListenerContainerFactory(env);

        factory.setConsumerCustomizer((id, builder) -> {
            //Uiteraard is het waarschijnlijk niet handig om altijd vanaf het begin te lezen, maar het
            //is wel -het- grote verschil in vergelijking tot reguliere messaging
            builder.name("consumeAllTheThings")
                    .offset(OffsetSpecification.first())
                    .manualTrackingStrategy();
        });

        return factory;
    }

}
