package org.hum.pumpkin.transport;

/**
 * Transporter只专注做传输。
 */
public interface Transporter {

	/**
	 * 打开服务
	 * @param port
	 */
	void open(int port);
	
	/**
	 * 发布服务 
	 * @param name (最初设计时采用Class, 但为了日后扩展异构调用，还是觉得name好一些)
	 * @param instances
	 */
	void export(Class<?> classType, Object instances);
}
