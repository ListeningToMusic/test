package com.app.mq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messageProducer")
public class MessageProducer {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AmqpTemplate amqpTemplate;

	public void sendMessage(Object message) {
		logger.debug("send message: {}", message);

		amqpTemplate.convertAndSend("my.direct", "email_queue_key", message);
	}

	public void sendMessage(String routingKey, Object message) {
		logger.debug("send message: {}", message);

		amqpTemplate.convertAndSend("my.direct", routingKey, message);
	}

	public void sendMessage(String exchange, String routingKey, Object message) {
		logger.debug("send message: {}", message);

		amqpTemplate.convertAndSend(exchange, routingKey, message);
	}
}
