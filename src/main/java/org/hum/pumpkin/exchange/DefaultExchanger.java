package org.hum.pumpkin.exchange;

import org.hum.pumpkin.exchange.server.ExchangeServer;
import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.transport.Transporter;
import org.hum.pumpkin.transport.factory.TransporterFactory;

public class DefaultExchanger extends AbstractExchanger {
	
	private final TransporterFactory transporterFactory = ServiceLoaderHolder.loadByCache(TransporterFactory.class);

	@Override
	protected ExchangeServer doBind(URL url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Transporter doConnect(URL url) {
		return transporterFactory.create(url);
	}
}
