import asyncio
import pika
from pika.adapters.asyncio_connection import AsyncioConnection

def create_connection():
    f = asyncio.Future()
    
    def on_open(connection):
        print('setting result')
        f.set_result(connection)

    def on_error(exception):
        print('setting error')
        f.set_exception(exception)

    def on_close(connection, exception):
        connection.ioloop.stop()
            
    AsyncioConnection(on_open_callback=on_open, on_close_callback=on_close, on_open_error_callback=on_error)    
    return f

def create_channel(connection):
    f = asyncio.Future()

    def on_open(channel):
        f.set_result(channel)
    
    connection.channel(on_open_callback=on_open)
    return f

def declare_queue(channel, name):
    f = asyncio.Future()

    def on_declared(queue):
        f.set_result(queue)

    channel.queue_declare(
        queue=name, 
        durable=True, 
        exclusive=False, 
        auto_delete=False, callback=on_declared)
    return f
    
def publish_text_message(channel, queue, message, exchange = ''):
    channel.basic_publish(exchange, queue, message, pika.BasicProperties(content_type='text/plain'))