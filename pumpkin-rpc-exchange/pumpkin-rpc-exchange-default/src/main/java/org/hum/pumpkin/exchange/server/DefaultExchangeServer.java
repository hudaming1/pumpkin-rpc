package org.hum.pumpkin.exchange.server;

import org.hum.pumpkin.transport.server.Server;

public class DefaultExchangeServer implements ExchangeServer {

	private Server server;

	public DefaultExchangeServer(Server server) {
		this.server = server;
		this.server.open();
	}
	
	@Override
	public void close() {
		server.close();
	}
}
