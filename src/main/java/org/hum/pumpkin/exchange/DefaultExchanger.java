package org.hum.pumpkin.exchange;

import org.hum.pumpkin.common.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.exchange.server.ExchangeServerHandler;
import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.transport.Client;
import org.hum.pumpkin.transport.Server;
import org.hum.pumpkin.transport.factory.TransporterFactory;

public class DefaultExchanger extends AbstractExchanger {
	
	private final TransporterFactory transporterFactory = ServiceLoaderHolder.loadByCache(TransporterFactory.class);

	@Override
	protected Server doBind(URL url, ExchangeServerHandler serverHandler) {
		return transporterFactory.createServer(url, serverHandler.getServerHandler());
	}

	@Override
	protected Client doConnect(URL url) {
		return transporterFactory.createClient(url);
	}
}
