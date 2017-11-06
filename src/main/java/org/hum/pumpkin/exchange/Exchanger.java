package org.hum.pumpkin.exchange;

import org.hum.pumpkin.exchange.client.ExchangeClient;
import org.hum.pumpkin.exchange.server.ExchangeServer;
import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.transport.ServerHandler;

public interface Exchanger {

	public ExchangeServer bind(URL url, ServerHandler serverHandler);
	
	public ExchangeClient connect(URL url);
}
