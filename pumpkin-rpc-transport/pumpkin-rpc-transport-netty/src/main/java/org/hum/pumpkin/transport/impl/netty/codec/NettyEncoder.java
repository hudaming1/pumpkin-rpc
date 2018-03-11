package org.hum.pumpkin.transport.impl.netty.codec;

import java.io.ByteArrayOutputStream;

import org.hum.pumpkin.serialization.Serialization;

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
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		serialization.serialize(bos, msg);
		out.writeBytes(bos.toByteArray());
	}
}