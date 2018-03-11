package org.hum.pumpkin.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetUtils {

	public static String getLocalAddress() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}
}
