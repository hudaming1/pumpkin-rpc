package org.hum.pumpkin.invoker;

public class RpcResult {

	private Object data;
	private Throwable error;

	public RpcResult(Object data, Throwable error) {
		this.data = data;
		this.error = error;
	}

	public Object get() {
		return data;
	}

	public Throwable getError() {
		return error;
	}
}
