package org.hum.pumpkin.transport;

public interface Transport {

	public void open();
	
	public RpcResult invoke(RpcInvocation invocation);
}
