package org.hum.pumpkin.exchange.server;

import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;
import org.hum.pumpkin.transport.ServerHandler;
import org.hum.pumpkin.transport.message.Header;
import org.hum.pumpkin.transport.message.Message;
import org.hum.pumpkin.transport.message.MessageBack;
import org.hum.pumpkin.transport.message.MessageTypeEnum;

/**
 * Server端的Exchange层可能有些鸡肋，实现的目的仅是为了解耦Protocol和Transport层。
 * 我不希望在Protocol层出现Message和MessageBack对象，因为他们是Transport层的class。
 */
public abstract class AbstractExchangeServerHandler implements ExchangeServerHandler {

	private ServerHandler serverHandler = new ServerHandler() {
		@Override
		public MessageBack received(Message message) {
			if (message.getHeader().getType() == MessageTypeEnum.Service.getCode()) {
				Request request = (Request) message.getBody();
				Response response = handler(request);
				return new MessageBack(new Header(message.getHeader().getMessageId(), MessageTypeEnum.Service.getCode()), response);
			}
			// TODO
			throw new RuntimeException("un implements code");
		}
	};
	
	@Override
	public ServerHandler getServerHandler() {
		return serverHandler;
	}
}
