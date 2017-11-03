package org.hum.pumpkin.invoker;

@SuppressWarnings("rawtypes")
public class RpcInvocation {

	private String method;
	private Class[] paramTypes;
	private Object[] params;

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
