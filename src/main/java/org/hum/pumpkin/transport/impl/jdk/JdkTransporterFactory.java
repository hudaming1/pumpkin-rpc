package org.hum.pumpkin.transport.impl.jdk;

import java.io.IOException;

import org.hum.pumpkin.common.RpcException;
import org.hum.pumpkin.transport.Transporter;
import org.hum.pumpkin.transport.config.TransporterConfig;
import org.hum.pumpkin.transport.factory.AbstractTransporterFactory;

public class JdkTransporterFactory extends AbstractTransporterFactory {
	
	@Override
	protected Transporter doCreate(final TransporterConfig config) {
		try {
			if (config.isKeepAlive()) {
				return new JdkKeeyAliveTranporter(config);
			} else {
				return new JdkShortTransporter(config);
			}
		} catch (IOException e) {
			throw new RpcException("create jdk transporter[" + config.getAddress() + ":" + config.getPort() + "] error", e);
		}
	}
}
