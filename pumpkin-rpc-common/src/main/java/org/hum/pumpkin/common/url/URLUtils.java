package org.hum.pumpkin.common.url;

public class URLUtils {
	
	public static Integer getAuthType(URL url) {
		return url.getInteger(URLConstant.AUTH_TYPE);
	}
}
