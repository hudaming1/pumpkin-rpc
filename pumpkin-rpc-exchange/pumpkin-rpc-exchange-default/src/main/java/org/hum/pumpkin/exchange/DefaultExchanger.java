package org.hum.pumpkin.exchange;

import org.hum.pumpkin.common.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.exchange.server.ExchangeServerHandler;
import org.hum.pumpkin.transport.client.Client;
import org.hum.pumpkin.transport.factory.TransporterFactory;
import org.hum.pumpkin.transport.message.Message;
import org.hum.pumpkin.transport.message.MessageBack;
import org.hum.pumpkin.transport.message.MessageTypeEnum;
import org.hum.pumpkin.transport.server.Server;
import org.hum.pumpkin.transport.server.ServerHandler;

public class DefaultExchanger extends AbstractExchanger {
	
	private final TransporterFactory transporterFactory = ServiceLoaderHolder.loadByCache(TransporterFactory.class);

	@Override
	protected Server doBind(final URL url, final ExchangeServerHandler serverHandler) {
		return transporterFactory.createServer(url, new ServerHandler() {
			@Override
			public MessageBack received(String host, Message message) {
				if (message.getHeader().getType() == MessageTypeEnum.Business.getCode()) {
					Request request = (Request) message.getBody();
					Response response = serverHandler.handler(request);
					return new MessageBack(message.getHeader(), response);
				}
				throw new UnsupportedOperationException("unimplements type " + message.getHeader().getType());
			}
		});
	}

	@Override
	protected Client doConnect(URL url) {
		return transporterFactory.createClient(url);
	}
}
