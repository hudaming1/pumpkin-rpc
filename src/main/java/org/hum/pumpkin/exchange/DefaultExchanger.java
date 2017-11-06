package org.hum.pumpkin.exchange;

import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.transport.ServerHandler;
import org.hum.pumpkin.transport.Transporter;
import org.hum.pumpkin.transport.TransporterServer;
import org.hum.pumpkin.transport.factory.TransporterFactory;

public class DefaultExchanger extends AbstractExchanger {
	
	private final TransporterFactory transporterFactory = ServiceLoaderHolder.loadByCache(TransporterFactory.class);

	@Override
	protected TransporterServer doBind(URL url, ServerHandler serverHandler) {
		return transporterFactory.createServer(url, serverHandler);
	}

	@Override
	protected Transporter doConnect(URL url) {
		return transporterFactory.createTransporter(url);
	}
}
