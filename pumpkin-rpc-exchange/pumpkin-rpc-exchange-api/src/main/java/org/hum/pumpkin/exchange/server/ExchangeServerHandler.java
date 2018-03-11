package org.hum.pumpkin.exchange.server;

import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;
import org.hum.pumpkin.transport.server.ServerHandler;

public interface ExchangeServerHandler {
	
	public Response handler(Request request);

	public ServerHandler getServerHandler();
}
