package org.hum.pumpkin.transport;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;
import org.hum.pumpkin.transport.message.Header;
import org.hum.pumpkin.transport.message.Message;
import org.hum.pumpkin.transport.message.MessageBack;
import org.hum.pumpkin.transport.message.MessageTypeEnum;

public abstract class AbstractServerHandler implements ServerHandler {
	
	private final Set<String> authedHost = new ConcurrentSkipListSet<>();
	
	@Override
	public MessageBack received(String host, Message message) {
		if (message.getHeader().getType() == MessageTypeEnum.Handshack.getCode()) {
			if (acceptConnection(host, message.getBody() == null ? null: message.getBody().toString())) {
				authedHost.add(host);
				return new MessageBack(new Header(message.getHeader().getMessageId(), MessageTypeEnum.Handshack.getCode()), true);
			}
		} 
		// 如果客户端没有经过握手鉴权，则返回NoAuth
		if (!authedHost.contains(host)) {
			return new MessageBack(new Header(message.getHeader().getMessageId(), MessageTypeEnum.Fail.getCode()), "no auth!");
		} else if (message.getHeader().getType() == MessageTypeEnum.Heartbeat.getCode()) {
			System.out.println("receive host[" + host + "] heartbeat!");
			return null;
		} else if (message.getHeader().getType() == MessageTypeEnum.Business.getCode()) {
			// 处理业务请求
			Request request = (Request) message.getBody();
			return new MessageBack(new Header(message.getHeader().getMessageId(), MessageTypeEnum.Business.getCode()), received(request));
		}
		throw new RuntimeException("unimplements code");
	}
	
	public abstract Response received(Request request);

	public abstract boolean acceptConnection(String host, String auth);
}
