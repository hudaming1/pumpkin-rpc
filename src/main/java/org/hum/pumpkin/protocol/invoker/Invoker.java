package org.hum.pumpkin.protocol.invoker;

public interface Invoker<T> {
	
	Class<T> getType();

	RpcResult invoke(RpcInvocation invocation);
}
