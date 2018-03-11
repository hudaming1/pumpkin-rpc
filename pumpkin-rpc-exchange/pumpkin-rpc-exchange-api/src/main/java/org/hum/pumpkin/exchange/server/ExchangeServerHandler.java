package org.hum.pumpkin.exchange.server;

import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;

public interface ExchangeServerHandler {
	
	public Response handler(Request request);
}
