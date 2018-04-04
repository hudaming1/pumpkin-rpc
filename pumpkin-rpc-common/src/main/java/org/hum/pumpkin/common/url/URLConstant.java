package org.hum.pumpkin.common.url;

import org.hum.pumpkin.common.url.enumtype.EAuthType;

public class URLConstant {

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

	/**
	 * 鉴权类型 {@link EAuthType}
	 */
	public static final String AUTH_TYPE = "auth_type";
	
	// 注册中心
	public static final String REGISTRY_NAME = "registryName";
	public static final String REGISTRY_ADDRESS = "registry_address";
	public static final String REGISTRY_PORT = "registry_port";
	
	// 序列化key
	public static final String SERIALIZATION = "serialization";

	// 传输协议
	public static final String TRANSPORT_KEY = "transporter";
}
