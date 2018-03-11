package org.hum.pumpkin.transport.server;

public abstract class AbstractServerHandler implements ServerHandler {
	
	// TODO 整段逻辑应该放到上层去处理（transport只负责处理传输，关于传输上的逻辑还是应该交给exchange层，况且也避免了transport层出现exchange的response、request）
//	@Override
//	public MessageBack received(String host, Message message) {
//		if (message.getHeader().getType() == MessageTypeEnum.Business.getCode()) {
//			// 处理业务请求
//			return new MessageBack(new Header(message.getHeader().getMessageId(), MessageTypeEnum.Business.getCode()), received(message.getBody()));
//		} else if (message.getHeader().getType() == MessageTypeEnum.Handshack.getCode()) {
//			return new MessageBack(new Header(message.getHeader().getMessageId(), MessageTypeEnum.Handshack.getCode()), true);
//		} else if (message.getHeader().getType() == MessageTypeEnum.Heartbeat.getCode()) {
//			// TODO 握手策略：服务器无需return
//			return new MessageBack(new Header(message.getHeader().getMessageId(), MessageTypeEnum.Heartbeat.getCode()), true);
//		}
//		throw new RuntimeException("unimplements code, ");
//	}
//	
//	public abstract Response received(Request request);
}
