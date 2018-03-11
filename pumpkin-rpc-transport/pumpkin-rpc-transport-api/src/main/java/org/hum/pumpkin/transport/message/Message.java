package org.hum.pumpkin.transport.message;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = -3435467751255965667L;
	private Header header;
	private Object body;

	public Message() {}
	
	public Message(Header header, Object body) {
		this.header = header;
		this.body = body;
	}

	public Header getHeader() {
		return header;
	}

	public Object getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "Message [header=" + header + ", body=" + body + "]";
	}
}
