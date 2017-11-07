package org.hum.pumpkin.transport.impl.netty;

import org.hum.pumpkin.transport.serialization.Serialization;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

@SuppressWarnings("rawtypes")
public class NettyEncoder extends MessageToByteEncoder {

	private Serialization serialization;

	public NettyEncoder(Serialization serialization) {
		this.serialization = serialization;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
		out.writeBytes(serialization.serialize(msg));
	}
}