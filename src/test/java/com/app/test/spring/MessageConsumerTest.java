package com.app.test.spring;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageConsumerTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-base.xml",
				"applicationContext-rabbitmq-consumer.xml");

		context.start();

		System.in.read(); // 为保证服务一直开着, 利用输入流的阻塞来模拟.
	}
}
