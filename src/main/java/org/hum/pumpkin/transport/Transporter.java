package org.hum.pumpkin.transport;

import org.hum.pumpkin.transport.bean.RpcInvocation;
import org.hum.pumpkin.transport.bean.RpcResult;

public interface Transporter {

	void open(int port);
	
	/**
	 * 发布服务 
	 * @param name (最初设计时采用Class, 但为了日后扩展异构调用，还是觉得name好一些)
	 * @param instances
	 */
	void export(String name, Object instances);
	
	RpcResult invoke(RpcInvocation invocation);
}
