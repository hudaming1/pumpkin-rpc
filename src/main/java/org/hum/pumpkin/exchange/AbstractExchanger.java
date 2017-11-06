package org.hum.pumpkin.exchange;

import org.hum.pumpkin.exchange.client.ExchangeClient;
import org.hum.pumpkin.exchange.client.DefaultExchangeClient;
import org.hum.pumpkin.exchange.server.ExchangeServer;
import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.transport.Transporter;

public abstract class AbstractExchanger implements Exchanger {

	@Override
	public ExchangeServer bind(URL url) {
		// TODO Auto-generated method stub
		return null;
	}

	protected abstract ExchangeServer doBind(URL url);

	@Override
	public ExchangeClient connect(URL url) {
		// TODO 判断isShare，复用相同连接
		return new DefaultExchangeClient(doConnect(url));
	}

	protected abstract Transporter doConnect(URL url);
}
