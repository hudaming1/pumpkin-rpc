package org.hum.pumpkin.exchange.server;

import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.transport.message.Message;
import org.hum.pumpkin.transport.message.MessageBack;
import org.hum.pumpkin.transport.server.AbstractServerHandler;
import org.hum.pumpkin.transport.server.ServerHandler;

/**
 * TODO 目前唯一的实现类居然是在上层Procotol里，需要看看怎么改合适
 * 对比Transporter负责传输，Exchange更专注于传输的逻辑，例如业务请求重试；
 * 服务启动后的握手，心跳监测逻辑都在Exchange处理
 */
public abstract class AbstractExchangeServerHandler implements ExchangeServerHandler {
	
	private URL url;
	
	public AbstractExchangeServerHandler(URL url) {
		this.url = url;
	}

	private ServerHandler serverHandler = new AbstractServerHandler() {
		@Override
		public MessageBack received(String host, Message message) {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	@Override
	public ServerHandler getServerHandler() {
		return serverHandler;
	}
}
