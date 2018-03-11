package org.hum.pumpkin.transport.impl.netty;

import org.hum.pumpkin.transport.message.Message;
import org.hum.pumpkin.transport.message.MessageBack;
import org.hum.pumpkin.transport.server.ServerHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyServerHandler extends SimpleChannelInboundHandler<Message> {
	
	private ServerHandler serverHandler;
	
	public NettyServerHandler(ServerHandler serverHandler) {
		this.serverHandler = serverHandler;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {
		MessageBack messageBack = serverHandler.received(ctx.channel().remoteAddress().toString(), message);
		if (messageBack != null) {
			ctx.writeAndFlush(messageBack);
		}
	}
}
