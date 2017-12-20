package com.app.mq.convert;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;

import com.alibaba.fastjson.JSON;
import com.app.mq.message.RabbitMessage;

public class FastJsonMessageConverter extends AbstractMessageConverter {
	private static Logger logger = LoggerFactory.getLogger(FastJsonMessageConverter.class);

	public static final String DEFAULT_CHARSET = "UTF-8";

	private volatile String defaultCharset = DEFAULT_CHARSET;

	public FastJsonMessageConverter() {
		super();
	}

	public void setDefaultCharset(String defaultCharset) {
		this.defaultCharset = (defaultCharset != null) ? defaultCharset : DEFAULT_CHARSET;
	}

	public Object fromMessage(Message message) throws MessageConversionException {
		String json = "";

		try {
			json = new String(message.getBody(), defaultCharset);

			logger.debug("json: {}", json);
			
			return JSON.parseObject(json, RabbitMessage.class);
		} catch (UnsupportedEncodingException e) {
			logger.error("{}", e);
		}

		return null;
	}

	protected Message createMessage(Object objectToConvert, MessageProperties messageProperties)
			throws MessageConversionException {
		byte[] bytes = null;

		try {
			String jsonString = JSON.toJSONString(objectToConvert);
			bytes = jsonString.getBytes(this.defaultCharset);
		} catch (UnsupportedEncodingException e) {
			logger.error("{}", e);
			throw new MessageConversionException("Failed to convert Message content", e);
		}

		messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
		messageProperties.setContentEncoding(this.defaultCharset);

		if (bytes != null) {
			messageProperties.setContentLength(bytes.length);
		}

		return new Message(bytes, messageProperties);
	}
}
