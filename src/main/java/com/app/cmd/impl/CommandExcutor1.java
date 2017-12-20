package com.app.cmd.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.app.cmd.CommandExcutor;
import com.app.mq.message.RabbitMessage;

@Service("commandExcutor1")
public class CommandExcutor1 implements CommandExcutor {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Integer getCmd() {
		return 1;
	}

	public void execute(RabbitMessage message) throws Exception {
		logger.info("CommandExcutor1.execute(), {}", message);
	}

}
