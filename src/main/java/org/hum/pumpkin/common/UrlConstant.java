package org.hum.pumpkin.common;

public class UrlConstant {

	// 是否保持长连接
	public static final String IS_KEEP_ALIVE = "is_keep_alive";
	
	/**
	 * 是否允许Invoker共享连接
	 * 共享连接的意思是指：假如客户端引用(refer)2个interface指向同一个地址(host+port)，那么
	 * 是否允许两个两个类型公用一个连接。当然，共享连接的前提是要保持长连接
	 */
	public static final String IS_SHARE_CONNECTION = "is_share_connection";
	
	// 重试次数
	public static final String RETRY_TIMES = "retry_times";
	
	// 是否采用异步请求
	public static final String IS_ASYN_REQUEST = "is_asyn_request";
}
