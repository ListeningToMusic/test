package com.app.cmd;

import com.app.mq.message.RabbitMessage;

/**
 * 业务逻辑处理接口.
 * 
 * @author wujiahuai
 *
 */
public interface CommandExcutor {
	/**
	 * 取得指令.
	 * 
	 * @return
	 */
	Integer getCmd();

	/**
	 * 处理业务逻辑.
	 * 
	 * @param message
	 * @throws Exception
	 */
	void execute(RabbitMessage message) throws Exception;
}
