package org.hum.pumpkin.transport;

import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;
import org.hum.pumpkin.transport.message.Header;
import org.hum.pumpkin.transport.message.Message;
import org.hum.pumpkin.transport.message.MessageBack;
import org.hum.pumpkin.transport.message.MessageTypeEnum;

public abstract class AbstractServerHandler implements ServerHandler {
	
	@Override
	public MessageBack received(String host, Message message) {
		if (message.getHeader().getType() == MessageTypeEnum.Business.getCode()) {
			// 处理业务请求
			Request request = (Request) message.getBody();
			return new MessageBack(new Header(message.getHeader().getMessageId(), MessageTypeEnum.Business.getCode()), received(request));
		}
		throw new RuntimeException("unimplements code");
	}
	
	public abstract Response received(Request request);
}
