package org.hum.pumpkin.exchange;

public class Response {

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
