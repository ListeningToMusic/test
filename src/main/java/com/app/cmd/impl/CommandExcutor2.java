package com.app.cmd.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.app.cmd.CommandExcutor;
import com.app.mq.message.RabbitMessage;

@Service("commandExcutor2")
public class CommandExcutor2 implements CommandExcutor {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public Integer getCmd() {
		return 2;
	}

	public void execute(RabbitMessage message) throws Exception {
		logger.info("CommandExcutor2.execute(), {}", message);
	}
}
