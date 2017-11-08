package org.hum.pumpkin.transport.impl.netty;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hum.pumpkin.common.exception.RpcException;
import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;
import org.hum.pumpkin.transport.ClientHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyClientHandler extends SimpleChannelInboundHandler<Response> implements ClientHandler {

	private volatile ChannelHandlerContext ctx;
	private Map<Long, MessageCallback> msgCallbackMap = new ConcurrentHashMap<>();

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		this.ctx = ctx;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, Response response) throws Exception {
		MessageCallback callBack = msgCallbackMap.get(response.getRequestId());
		callBack.finish(response);
	}

	@Override
	public Response send(Request request) {
		while (ctx == null) {
			// blocked
		}
		
		MessageCallback messageCallback = new MessageCallback();
		msgCallbackMap.put(request.getId(), messageCallback);
		ctx.writeAndFlush(request);
		try {
			return messageCallback.get();
		} catch (InterruptedException e) {
			throw new RpcException(null, e);
		}
	}
}