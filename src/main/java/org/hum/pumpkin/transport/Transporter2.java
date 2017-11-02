package org.hum.pumpkin.transport;

import org.hum.pumpkin.transport.bean.RpcInvocation;
import org.hum.pumpkin.transport.bean.RpcResult;

/**
 * Transporter只专注做传输。
 */
public interface Transporter2 {

	/**
	 * 打开服务
	 * @param port
	 */
	void open(int port);
	
	/**
	 * 发布服务 
	 * @param classType (TODO 最初设计时采用classType, 但为了日后扩展异构调用，还是觉得name(String)好一些)
	 * @param instances
	 */
	void export(Class<?> classType, Object instances);
	
	/**
	 * 调用
	 * @param invocation
	 * @return
	 */
	RpcResult invoke(RpcInvocation invocation);
}
