package org.hum.pumpkin.protocol;

import org.hum.pumpkin.common.UrlConstant;
import org.hum.pumpkin.exporter.Exporter;
import org.hum.pumpkin.invoker.Invoker;
import org.hum.pumpkin.invoker.direct.DirectInvoker;
import org.hum.pumpkin.server.ServiceConfig;

public class PumpkinProtocol implements Protocol {
	
	@Override
	public <T> Invoker<T> refer(Class<T> classType, URL url) {
		url.buildParam(UrlConstant.IS_KEEP_ALIVE, true);
		return new DirectInvoker<>(classType, url);
	}

	@Override
	public <T> Exporter<T> export(ServiceConfig<T> serviceConfig) {
		return null;
	}
}
