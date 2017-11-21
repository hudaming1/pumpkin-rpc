package org.hum.pumpkin.transport.impl.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import org.hum.pumpkin.common.exception.RpcException;
import org.hum.pumpkin.transport.ClientHandler;
import org.hum.pumpkin.transport.message.Header;
import org.hum.pumpkin.transport.message.Message;
import org.hum.pumpkin.transport.message.MessageBack;
import org.hum.pumpkin.transport.message.MessageTypeEnum;

public class NettyClientHandler extends SimpleChannelInboundHandler<MessageBack> implements ClientHandler {

	private CountDownLatch isStart = new CountDownLatch(1);
	private volatile ChannelHandlerContext ctx;
	private Map<Long, MessageCallback> msgCallbackMap = new ConcurrentHashMap<>();

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		this.ctx = ctx;
		Message message = new Message(new Header(System.currentTimeMillis(), MessageTypeEnum.Handshack.getCode()), null);
		ctx.writeAndFlush(message);
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext arg0, MessageBack messageBack) throws Exception {
		// 如果握手失败，则关闭连接
		if (messageBack.getHeader().getType() == MessageTypeEnum.Handshack.getCode()) {
			if ((Boolean) messageBack.getBody() == false) {
				ctx.close();
				return ;
			} else {
				isStart.countDown();
			}
		} else {
			MessageCallback callBack = msgCallbackMap.get(messageBack.getHeader().getMessageId());
			callBack.finish(messageBack);
		}
	}

	@Override
	public MessageBack send(Message message) {
		try {
			isStart.await();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		MessageCallback messageCallback = new MessageCallback();
		msgCallbackMap.put(message.getHeader().getMessageId(), messageCallback);
		ctx.writeAndFlush(message);
		try {
			return messageCallback.get();
		} catch (InterruptedException e) {
			throw new RpcException(null, e);
		}
	}
}