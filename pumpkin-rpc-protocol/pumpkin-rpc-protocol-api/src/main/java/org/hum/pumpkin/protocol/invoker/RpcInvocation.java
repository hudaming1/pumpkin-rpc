package org.hum.pumpkin.protocol.invoker;

import java.io.Serializable;
import java.util.Arrays;

@SuppressWarnings("rawtypes")
public class RpcInvocation implements Serializable {

	private static final long serialVersionUID = 6668787664662887722L;
	private long invocationId;
	private Class interfaceType;
	private String method;
	private Class[] paramTypes;
	private Object[] params;
	
	public RpcInvocation() {
		invocationId = System.currentTimeMillis();
	}

	public RpcInvocation(Class interfaceType, String method, Class[] paramTypes, Object[] params) {
		this.interfaceType = interfaceType;
		this.method = method;
		this.paramTypes = paramTypes;
		this.params = params;
		invocationId = System.currentTimeMillis();
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
	
	public long getInvocationId() {
		return invocationId;
	}

	public Class getInterfaceType() {
		return interfaceType;
	}

	@Override
	public String toString() {
		return "RpcInvocation [invocationId=" + invocationId + ", interfaceType=" + interfaceType + ", method=" + method
				+ ", paramTypes=" + Arrays.toString(paramTypes) + ", params=" + Arrays.toString(params) + "]";
	}
}
