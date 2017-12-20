package com.app.test.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.app.mq.message.RabbitMessage;
import com.app.mq.producer.MessageProducer;
import com.app.utils.IdGen;

public class MessageProducerTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-base.xml",
				"applicationContext-rabbitmq-producer.xml");

		context.start();

		MessageProducer messageProducer = (MessageProducer) context.getBean("messageProducer");
		RabbitMessage rabbitMessage = new RabbitMessage();

		for (int i = 0; i < 100; i++) {
			rabbitMessage.setCmd(1);
			rabbitMessage.setHead("head");
			rabbitMessage.setKey(IdGen.uuid());
			rabbitMessage.setLength(1000);
			rabbitMessage.setLocalIP("10.0.0.100");
			rabbitMessage.setPlayLoad("Hello, I am amq sender *** email_queue *** _" + i);
			rabbitMessage.setPt("pt");
			rabbitMessage.setRemoteIP("192.168.0.10");
			rabbitMessage.setRemotPort(9090);
			rabbitMessage.setSessionID(IdGen.uuid());

			messageProducer.sendMessage(rabbitMessage);
			
			rabbitMessage.setCmd(2);
			rabbitMessage.setPlayLoad("Hello, I am amq sender *** sms_queue *** _" + i);
			
			messageProducer.sendMessage("sms_queue_key", rabbitMessage);

			Thread.sleep(2000);
		}
	}
}
