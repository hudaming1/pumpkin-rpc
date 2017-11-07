package org.hum.pumpkin.transport.impl.netty;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.hum.pumpkin.serialization.Serialization;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class NettyDecoder<T> extends ByteToMessageDecoder {

	private Serialization serialization;
	private Class<T> clazz;

	public NettyDecoder(Class<T> clazz, Serialization serialization) {
		this.clazz = clazz;
		this.serialization = serialization;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> list) throws Exception {
		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);
		list.add(serialization.deserialize(new ByteArrayInputStream(bytes), clazz));
	}
}