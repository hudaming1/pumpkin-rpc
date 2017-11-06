package org.hum.pumpkin.transport.impl.jdk;

import java.io.IOException;

import org.hum.pumpkin.common.RpcException;
import org.hum.pumpkin.common.UrlConstant;
import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.transport.Transporter;
import org.hum.pumpkin.transport.factory.AbstractTransporterFactory;

public class JdkTranspoterFactory extends AbstractTransporterFactory {

	@Override
	protected Transporter doCreate(URL url) {
		try {
			Boolean isKeepAlive = url.getBoolean(UrlConstant.IS_KEEP_ALIVE);
			if (isKeepAlive != null && isKeepAlive) {
				return new JdkKeeyAliveTranporter(url);
			} else {
				return new JdkShortTransporter(url);
			}
		} catch (IOException e) {
			throw new RpcException("create jdk transporter[" + url.getHost() + ":" + url.getPort() + "] error", e);
		}
	}
}
