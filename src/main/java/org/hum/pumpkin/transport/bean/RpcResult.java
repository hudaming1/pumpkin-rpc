package org.hum.pumpkin.transport.bean;

import java.io.Serializable;

public class RpcResult implements Serializable {

	private static final long serialVersionUID = -1334929202169465392L;
	
	private Long id;
	private Object result;
	private Throwable error;

	public RpcResult(Long id, Object result, Throwable error) {
		this.id = id;
		this.result = result;
		this.error = error;
	}

	public Long getId() {
		return id;
	}

	public Object getResult() {
		return result;
	}

	public Throwable getError() {
		return error;
	}
}
