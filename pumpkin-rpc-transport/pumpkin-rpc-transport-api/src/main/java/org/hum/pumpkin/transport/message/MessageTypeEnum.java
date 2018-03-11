package org.hum.pumpkin.transport.message;

public enum MessageTypeEnum {

	Fail(0, "异常请求"), Handshack(1, "握手请求"), Heartbeat(2, "心跳请求"), Business(3, "业务请求");

	private byte code;
	private String desc;

	MessageTypeEnum(Integer code, String desc) {
		this.code = code.byteValue();
		this.desc = desc;
	}

	public byte getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
