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
		System.out.println(message);
		if (message.getHeader().getType() == MessageTypeEnum.Handshack.getCode()) {
			if (acceptConnection(host, message.getBody() == null ? null: message.getBody().toString())) {
				authedHost.add(host);
				return new MessageBack(new Header(message.getHeader().getMessageId(), MessageTypeEnum.Handshack.getCode()), true);
			}
		} 
		if (!authedHost.contains(host)) {
			return new MessageBack(new Header(message.getHeader().getMessageId(), MessageTypeEnum.Fail.getCode()), "no auth!");
		}
		if (message.getHeader().getType() == MessageTypeEnum.Business.getCode()) {
			Request request = (Request) message.getBody();
			return new MessageBack(new Header(message.getHeader().getMessageId(), MessageTypeEnum.Business.getCode()), received(request));
		}
		throw new RuntimeException("unimplements code");
	}
	
	public abstract Response received(Request request);

	public abstract boolean acceptConnection(String host, String auth);
}
