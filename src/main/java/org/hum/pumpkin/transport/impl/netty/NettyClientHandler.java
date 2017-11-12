package org.hum.pumpkin.transport.impl.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hum.pumpkin.common.exception.RpcException;
import org.hum.pumpkin.transport.ClientHandler;
import org.hum.pumpkin.transport.message.Message;
import org.hum.pumpkin.transport.message.MessageBack;

public class NettyClientHandler extends SimpleChannelInboundHandler<MessageBack> implements ClientHandler {

	private volatile ChannelHandlerContext ctx;
	private Map<Long, MessageCallback> msgCallbackMap = new ConcurrentHashMap<>();

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		this.ctx = ctx;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, MessageBack messageBack) throws Exception {
		MessageCallback callBack = msgCallbackMap.get(messageBack.getHeader().getMessageId());
		callBack.finish(messageBack);
	}

	@Override
	public MessageBack send(Message message) {
		while (ctx == null) {
			// blocked
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