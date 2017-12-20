package com.app.mq.message;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class RabbitMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String head = "hd"; // 数据头.
	private String pt; // 协议类型.
	private String playLoad; // 有效数据.
	private Integer length = 42; // 固定长度.
	private Integer cmd;
	private String sessionID; // 32个字符长度.
	private String key;
	private String localIP;
	private String remoteIP;
	private Integer remotPort;

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getPt() {
		return pt;
	}

	public void setPt(String pt) {
		this.pt = pt;
	}

	public String getPlayLoad() {
		return playLoad;
	}

	public void setPlayLoad(String playLoad) {
		this.playLoad = playLoad;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getCmd() {
		return cmd;
	}

	public void setCmd(Integer cmd) {
		this.cmd = cmd;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLocalIP() {
		return localIP;
	}

	public void setLocalIP(String localIP) {
		this.localIP = localIP;
	}

	public String getRemoteIP() {
		return remoteIP;
	}

	public void setRemoteIP(String remoteIP) {
		this.remoteIP = remoteIP;
	}

	public Integer getRemotPort() {
		return remotPort;
	}

	public void setRemotPort(Integer remotPort) {
		this.remotPort = remotPort;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
