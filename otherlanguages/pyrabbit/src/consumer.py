import asyncio
import pika

import asyncio
import pika
from pikawrapper import *


async def main():
    
    print('creating connection')
    connection = await create_connection()
    
    print('creating channel1')
    channel1 = await create_channel(connection)
    print('creating channel2')
    channel2 = await create_channel(connection)
    
    await declare_queue(channel1, 'test1')
    await declare_queue(channel2, 'test2')
    print('wut?')

    def accept_message(chan, header, props, body):
        print(header)
        print(props)
        print(body)
        chan.basic_ack(header.delivery_tag)

    channel1.basic_consume('test1', accept_message)
    channel2.basic_consume('test2', accept_message)
    
    # Dit kan vast netter...
    while(True):
        await asyncio.sleep(5)

asyncio.run(main())
