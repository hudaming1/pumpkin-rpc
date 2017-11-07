package org.hum.pumpkin.exchange;

import org.hum.pumpkin.exchange.client.ExchangeClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hum.pumpkin.common.UrlConstant;
import org.hum.pumpkin.exchange.client.DefaultExchangeClient;
import org.hum.pumpkin.exchange.server.DefaultExchangeServer;
import org.hum.pumpkin.exchange.server.ExchangeServer;
import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.transport.ServerHandler;
import org.hum.pumpkin.transport.Client;
import org.hum.pumpkin.transport.Server;

public abstract class AbstractExchanger implements Exchanger {

	private static final Map<String, ExchangeClient> EXCHANGE_CLIENTS = new ConcurrentHashMap<>();
	private static final Object createLock = new Object();
	
	@Override
	public ExchangeServer bind(URL url, ServerHandler serverHandler) {
		// TODO test版本 待完善
		Server transporterServer = doBind(url, serverHandler);
		return new DefaultExchangeServer(transporterServer);
	}

	protected abstract Server doBind(URL url, ServerHandler serverHandler);

	@Override
	public ExchangeClient connect(URL url) {
		Boolean isShare = url.getBoolean(UrlConstant.IS_SHARE_CONNECTION);
		if (isShare == null || isShare) {
			String serviceKey = getServiceKey(url);
			ExchangeClient exchangeClient = EXCHANGE_CLIENTS.get(serviceKey);
			if (exchangeClient != null) {
				return exchangeClient;
			}
			synchronized (createLock) {
				if (EXCHANGE_CLIENTS.containsKey(serviceKey)) {
					return EXCHANGE_CLIENTS.get(serviceKey);
				}
				exchangeClient = new DefaultExchangeClient(doConnect(url));
				EXCHANGE_CLIENTS.put(serviceKey, exchangeClient);
				return exchangeClient;
			}
		} else {
			return new DefaultExchangeClient(doConnect(url));
		}
	}

	protected abstract Client doConnect(URL url);
	
	private String getServiceKey(URL url) {
		return url.getHost() + ":" + url.getPort(); 
	}
}
