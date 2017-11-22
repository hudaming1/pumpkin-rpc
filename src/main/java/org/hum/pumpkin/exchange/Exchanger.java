package org.hum.pumpkin.exchange;

import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.exchange.client.ExchangeClient;
import org.hum.pumpkin.exchange.server.ExchangeServer;
import org.hum.pumpkin.exchange.server.ExchangeServerHandler;

public interface Exchanger {

	public ExchangeServer bind(URL url, ExchangeServerHandler serverHandler);
	
	public ExchangeClient connect(URL url);
}
