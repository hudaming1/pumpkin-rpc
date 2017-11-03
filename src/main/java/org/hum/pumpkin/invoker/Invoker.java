package org.hum.pumpkin.invoker;

public interface Invoker<T> {
	
	Class<T> getType();

	RpcResult invoke(RpcInvocation invocation);
}
