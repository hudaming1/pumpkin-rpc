package org.hum.pumpkin.transport.impl.netty.plugins;

import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.common.url.URLUtils;
import org.hum.pumpkin.common.url.enumtype.EAuthType;

public class AuthController {
	
	private EAuthType authType;

	public AuthController(URL url) {
		this.authType = EAuthType.getEnum(URLUtils.getAuthType(url));
	}

	public boolean check(String host, String msg) {
		if (authType == null) {
			return true;
		} else {
			// TODO EAuthType
			return false;
		}
	}
}
