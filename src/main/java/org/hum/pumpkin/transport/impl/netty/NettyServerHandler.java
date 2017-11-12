package org.hum.pumpkin.transport.impl.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.hum.pumpkin.transport.ServerHandler;
import org.hum.pumpkin.transport.message.Message;
import org.hum.pumpkin.transport.message.MessageBack;

public class NettyServerHandler extends SimpleChannelInboundHandler<Message> {
	
	private ServerHandler serverHandler;
	
	public NettyServerHandler(ServerHandler serverHandler) {
		this.serverHandler = serverHandler;
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {
		MessageBack messageBack = serverHandler.received(message);
		ctx.writeAndFlush(messageBack);
	}
}
