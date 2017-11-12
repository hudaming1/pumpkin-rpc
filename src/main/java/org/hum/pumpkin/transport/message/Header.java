package org.hum.pumpkin.transport.message;

import java.io.Serializable;

import org.hum.pumpkin.common.Constant;

public class Header implements Serializable {
	private static final long serialVersionUID = 3691820711930518636L;
	private int magicNum = Constant.PUMKIN_MAGIC_NUM;
	private long messageId;
	private byte type;

	public Header(long messageId, byte type) {
		this.messageId = messageId;
		this.type = type;
	}

	public int getMagicNum() {
		return magicNum;
	}

	public long getMessageId() {
		return messageId;
	}

	public byte getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Header [magicNum=" + magicNum + ", messageId=" + messageId + ", type=" + type + "]";
	}
}
