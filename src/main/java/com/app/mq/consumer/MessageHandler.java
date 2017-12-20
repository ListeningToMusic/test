package com.app.mq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.cmd.CommandExcutor;
import com.app.mq.message.RabbitMessage;
import com.app.utils.SpringContextHolder;

public class MessageHandler {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void handleMessage(RabbitMessage message) throws Exception {
		logger.info("message: {}", message);

		if (message != null && message.getCmd() != null) {
			CommandExcutor commandExcutor = SpringContextHolder.getExcutor(message.getCmd());

			if (commandExcutor != null) {
				commandExcutor.execute(message);
			}
		}
	}
}
