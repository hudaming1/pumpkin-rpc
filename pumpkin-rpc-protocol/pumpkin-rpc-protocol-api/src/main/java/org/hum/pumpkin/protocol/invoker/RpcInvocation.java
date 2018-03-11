package org.hum.pumpkin.protocol.invoker;

import java.io.Serializable;

@SuppressWarnings("rawtypes")
public class RpcInvocation implements Serializable {

	private static final long serialVersionUID = 6668787664662887722L;
	private String method;
	private Class[] paramTypes;
	private Object[] params;
	
	public RpcInvocation() {
	}

	public RpcInvocation(String method, Class[] paramTypes, Object[] params) {
		this.method = method;
		this.paramTypes = paramTypes;
		this.params = params;
	}

	public String getMethod() {
		return method;
	}

	public Class[] getParamTypes() {
		return paramTypes;
	}

	public Object[] getParams() {
		return params;
	}
}
