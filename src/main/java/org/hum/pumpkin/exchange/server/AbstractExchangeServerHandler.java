package org.hum.pumpkin.exchange.server;

import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;
import org.hum.pumpkin.transport.AbstractServerHandler;
import org.hum.pumpkin.transport.ServerHandler;
import org.hum.pumpkin.transport.message.Message;

/**
 * Server端的Exchange层可能有些鸡肋，实现的目的仅是为了解耦Protocol和Transport层。
 * 我不希望在Protocol层出现Message和MessageBack对象，因为他们是Transport层的class。
 */
public abstract class AbstractExchangeServerHandler implements ExchangeServerHandler {

	private ServerHandler serverHandler = new AbstractServerHandler() {
		
		@Override
		public Response received(Request request) {
			return handler(request);
		}

		@Override
		public boolean acceptConnection(String host, int port, Message message) {
			return false;
		}
	};
	
	@Override
	public ServerHandler getServerHandler() {
		return serverHandler;
	}
}
