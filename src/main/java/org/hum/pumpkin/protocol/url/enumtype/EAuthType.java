package org.hum.pumpkin.protocol.url.enumtype;

public enum EAuthType {
	
	WhiteList(1, "白名单鉴权"), Password(2, "密码鉴权"), Secret(3, "秘钥鉴权");

	private int code;
	private String desc;

	EAuthType(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	public static EAuthType getEnum(Integer code) {
		if (code == null) {
			return null;
		}
		for (EAuthType enumType : values()) {
			if (enumType.getCode() == code) {
				return enumType;
			}
		}
		return null;
	}
}
