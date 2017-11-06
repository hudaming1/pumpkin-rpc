package org.hum.pumpkin.exchange;

import org.hum.pumpkin.exchange.client.ExchangeClient;
import org.hum.pumpkin.exchange.server.ExchangeServer;
import org.hum.pumpkin.protocol.URL;

public interface Exchanger {

	public ExchangeServer bind(URL url);
	
	public ExchangeClient connect(URL url);
}
