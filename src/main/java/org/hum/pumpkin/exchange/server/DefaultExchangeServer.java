package org.hum.pumpkin.exchange.server;

import org.hum.pumpkin.transport.TransporterServer;

public class DefaultExchangeServer implements ExchangeServer {

	private TransporterServer transpoterServer;

	public DefaultExchangeServer(TransporterServer transpoterServer) {
		this.transpoterServer = transpoterServer;
		this.transpoterServer.open();
	}
	
	@Override
	public void close() {
		transpoterServer.close();
	}
}
