package org.hum.pumpkin.protocol.url;

import org.hum.pumpkin.protocol.url.enumtype.EAuthType;

public class URLUtils {

	/**
	 * 鉴权类型 {@link EAuthType}
	 */
	public static final String AUTH_TYPE = "auth_type";
	
	// 重试次数
	public static final String RETRIES = "retries";
	
	// 超时时间
	public static final String TIMEOUT = "timeout";
	
	public static Integer getAuthType(URL url) {
		return url.getInteger(AUTH_TYPE);
	}
}
