package nl.hu.ict.inno.streaming.consumer;

import com.rabbitmq.stream.Environment;
import com.rabbitmq.stream.OffsetSpecification;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.adapter.AmqpMessageHandlerMethodFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
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
    public Queue normalQueue(){
        return QueueBuilder.durable("someQueue")
                .build();
    }

    @Bean
    public RabbitStreamTemplate streamTemplate(Environment env){
        var template = new RabbitStreamTemplate(env, "someStream");
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    @Bean
    public MessageConverter converter(){
        return new CustomJacksonConverter();
    }


    @Bean
    RabbitListenerContainerFactory<StreamListenerContainer> streams(Environment env) {
        StreamRabbitListenerContainerFactory factory = new StreamRabbitListenerContainerFactory(env);

        factory.setConsumerCustomizer((id, builder) -> {
//builder.messageHandler()
            builder.name("myConsumer")

                    .offset(OffsetSpecification.first())
                    .manualTrackingStrategy();
        });

        return factory;
    }

    @Bean
    public MessageHandlerMethodFactory createDefaultMessageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory defaultFactory = new AmqpMessageHandlerMethodFactory();

        defaultFactory.setMessageConverter(converter());

        defaultFactory.afterPropertiesSet();
        return defaultFactory;
    }
//
//    @Bean
//    RabbitListenerContainerFactory<StreamListenerContainer> nativeFactory(Environment env) {
//        StreamRabbitListenerContainerFactory factory = new StreamRabbitListenerContainerFactory(env);
//        factory.setNativeListener(true);
//        factory.setConsumerCustomizer((id, builder) -> {
//            builder.name("myConsumer")
//                    .offset(OffsetSpecification.first())
//                    .manualTrackingStrategy();
//        });
//        return factory;
//    }
}
