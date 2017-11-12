package org.hum.pumpkin.transport.message;

public enum MessageTypeEnum {

	Handshack(Byte.parseByte("1"), "握手"), 
	Heartbeat(Byte.parseByte("2"), "心跳"), 
	Service(Byte.parseByte("3"), "业务请求");

	private Byte code;
	private String desc;

	MessageTypeEnum(Byte code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Byte getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
