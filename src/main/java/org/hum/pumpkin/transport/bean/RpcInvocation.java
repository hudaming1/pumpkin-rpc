package org.hum.pumpkin.transport.bean;

import java.io.Serializable;
import java.util.Arrays;

@SuppressWarnings("rawtypes")
public class RpcInvocation implements Serializable {

	private static final long serialVersionUID = 1903129926744790517L;
	private Long id;
	private Class clazz;
	private String method;
	private Class[] paramTypes;
	private Object[] params;

	public RpcInvocation(Class clazz, String method, Class[] paramTypes, Object[] params) {
		this.clazz = clazz;
		this.method = method;
		this.paramTypes = paramTypes;
		this.params = params;
		this.id = System.currentTimeMillis();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Class getClazz() {
		return clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Class[] getParamTypes() {
		return paramTypes;
	}

	public void setParamTypes(Class[] paramTypes) {
		this.paramTypes = paramTypes;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RpcInvocation [id=").append(id).append(", clazz=").append(clazz).append(", method=").append(method)
				.append(", paramTypes=").append(Arrays.toString(paramTypes)).append(", params=")
				.append(Arrays.toString(params)).append("]");
		return builder.toString();
	}
}
