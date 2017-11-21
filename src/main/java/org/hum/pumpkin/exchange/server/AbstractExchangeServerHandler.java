package org.hum.pumpkin.exchange.server;

import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;
import org.hum.pumpkin.protocol.url.URL;
import org.hum.pumpkin.protocol.url.URLUtils;
import org.hum.pumpkin.protocol.url.enumtype.EAuthType;
import org.hum.pumpkin.transport.AbstractServerHandler;
import org.hum.pumpkin.transport.ServerHandler;

/**
 * Server端的Exchange层可能有些鸡肋，实现的目的仅是为了解耦Protocol和Transport层。
 * 我不希望在Protocol层出现Message和MessageBack对象，因为他们是Transport层的class。
 */
public abstract class AbstractExchangeServerHandler implements ExchangeServerHandler {
	
	private URL url;
	
	public AbstractExchangeServerHandler(URL url) {
		this.url = url;
	}

	private ServerHandler serverHandler = new AbstractServerHandler() {
		
		@Override
		public Response received(Request request) {
			return handler(request);
		}

		@Override
		public boolean acceptConnection(String host, String auth) {
			if (EAuthType.getEnum(URLUtils.getAuthType(url)) == null) {
				// 不需要鉴权
				return true;
			}
			// TODO 其他鉴权尚未实现
			return false;
		}
	};
	
	@Override
	public ServerHandler getServerHandler() {
		return serverHandler;
	}
}
