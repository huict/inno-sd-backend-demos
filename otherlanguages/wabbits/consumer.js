import { Connection } from 'rabbitmq-client'

const rabbit = new Connection('amqp://guest:guest@localhost:5672');

["qwer", "zxcv", "poiu"].map(queue => {
    rabbit.createConsumer({
        queue: queue,
        queueOptions: {durable: true}
    }, async msg => console.log(msg))
});
