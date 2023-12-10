package nl.hu.ict.inno.streaming.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class RabbitRaw {
    //De UI van RabbitManagement laat geen messages zien, dus zo kunnen we in elk geval voor debugging purposes
    //even checken wat er op de Queue staat
    public static void main(String[] args) throws IOException, TimeoutException {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-queue-type", "stream");
//        arguments.put("x-max-length-bytes", 20_000_000_000); // maximum stream size: 20 GB
//        arguments.put("x-stream-max-segment-size-bytes", 100_000_000); // size of segment files: 100 MB

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(
                "someStream",
                true,         // durable
                false, false, // not exclusive, not auto-delete
                arguments
        );

        channel.basicQos(100); // QoS must be specified
        channel.basicConsume(
                "someStream",
                false,
                Collections.singletonMap("x-stream-offset", "first"), // "first" offset specification
                (consumerTag, message) -> {
                    System.out.println(new String(message.getBody()));
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false); // ack is required
                },
                consumerTag -> { });
        System.out.println("Declared Queue");
    }
}
