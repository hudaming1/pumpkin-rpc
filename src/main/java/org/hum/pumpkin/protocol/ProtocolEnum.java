package org.hum.pumpkin.protocol;

public enum ProtocolEnum {

	Pumpkin("pumpkin", "默认信息"), Registry("registry", "注册中心");

	private String name;
	private String desc;

	ProtocolEnum(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}
}
