package com.app.mq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class MessageConsumer implements MessageListener {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void onMessage(Message message) {
		logger.info("message: {}", message);
		logger.info("message.getBody(): {}", message.getBody());
	}
}
