package org.hum.pumpkin.transport.message;

import java.io.Serializable;

public class MessageBack implements Serializable {

	private static final long serialVersionUID = 8533775421917568498L;
	private Header header;
	private Object body;

	public MessageBack(Header header, Object body) {
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
		return "MessageBack [header=" + header + ", body=" + body + "]";
	}
}
