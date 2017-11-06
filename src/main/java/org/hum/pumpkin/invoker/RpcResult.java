package org.hum.pumpkin.invoker;

import java.io.Serializable;

public class RpcResult implements Serializable {

	private static final long serialVersionUID = -7639259023819054096L;
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
