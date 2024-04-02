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
    for _ in range(5):
        publish_text_message(channel1, 'test1', 'Hello World!')
        publish_text_message(channel2, 'test2', 'Bye World!')

asyncio.run(main())