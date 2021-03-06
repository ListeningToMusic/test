package com.app.test.java;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Receive {
	private final static String QUEUE_NAME = "MyQueue";
	private static boolean flag = false;

	public static void main(String[] args) throws InterruptedException {
		receive();
	}

	public static void receive() throws InterruptedException {
		ConnectionFactory factory = null;
		Connection connection = null;
		Channel channel = null;
		
		try {
			factory = new ConnectionFactory();
			factory.setHost("localhost");
			connection = factory.newConnection();
			channel = connection.createChannel();
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
						byte[] body) throws IOException {
					System.out.println("11111111111");
					String message = new String(body, "UTF-8");
					System.out.println("收到消息....." + message);
					
					flag = true;
				}
			};
			channel.basicConsume(QUEUE_NAME, true, consumer);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} finally {
			try {
				while(!flag) {
					Thread.sleep(1000);
				}
				
				// 关闭资源
				channel.close();
				connection.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
			}
		}
	}
}
