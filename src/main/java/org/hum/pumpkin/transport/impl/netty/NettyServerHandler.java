package org.hum.pumpkin.transport.impl.netty;

import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;
import org.hum.pumpkin.transport.ServerHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyServerHandler extends SimpleChannelInboundHandler<Request> {
	
	private ServerHandler serverHandler;
	
	public NettyServerHandler(ServerHandler serverHandler) {
		this.serverHandler = serverHandler;
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Request request) throws Exception {
		Response response = serverHandler.received(request);
		ctx.writeAndFlush(response);
	}
}
