import { Connection } from 'rabbitmq-client'

const rabbit = new Connection('amqp://guest:guest@localhost:5672');

const pub = rabbit.createPublisher({ confirm: true, });

await pub.send({exchange: '', routingKey: 'poiu'}, { message: 'hello world!'})
await pub.send({exchange: '', routingKey: 'qwer'}, { message: 'hello world!'})
await pub.send({exchange: '', routingKey: 'zxcv'}, { message: 'hello world!'})


await pub.close();

await rabbit.close();