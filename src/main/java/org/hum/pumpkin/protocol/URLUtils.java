package org.hum.pumpkin.protocol;

public class URLUtils {

	/**
	 * 鉴权类型
	 * 	null		无需鉴权，任何Consumer都可访问
	 * 	1		白名单鉴权
	 * 	2		用户名密码鉴权
	 * 	3		公钥私钥鉴权
	 */
	public static final String AUTH_TYPE = "auth_type";
	
	// 重试次数
	public static final String RETRIES = "retries";
	
	// 超时时间
	public static final String TIMEOUT = "timeout";
	
	public Integer getAuthType(URL url) {
		return (Integer) url.getParam(AUTH_TYPE);
	}
}
