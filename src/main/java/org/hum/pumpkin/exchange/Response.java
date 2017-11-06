package org.hum.pumpkin.exchange;

import java.io.Serializable;

public class Response implements Serializable {

	private static final long serialVersionUID = 8783577974211954621L;
	private Object data;
	private Throwable error;
	
	public Response(Object data, Throwable error) {
		this.data = data;
		this.error = error;
	}
	
	public Object getData() {
		return this.data;
	}
	
	public Throwable getError() {
		return this.error;
	}
}
