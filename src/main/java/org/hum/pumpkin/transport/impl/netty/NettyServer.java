package org.hum.pumpkin.transport.impl.netty;

import java.util.List;

import org.hum.pumpkin.common.RpcException;
import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;
import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.transport.Server;
import org.hum.pumpkin.transport.ServerHandler;
import org.hum.pumpkin.transport.serialization.Serialization;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

public class NettyServer implements Server {

	private URL url;
	private ServerHandler serverHandler;
	private final Serialization serialization = ServiceLoaderHolder.loadByCache(Serialization.class);

	public NettyServer(URL url, ServerHandler serverHandler) {
		this.url = url;
		this.serverHandler = serverHandler;
	}

	@Override
	public void open() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {

			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.childHandler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					ch.pipeline().addLast(new Encoder());
					ch.pipeline().addLast(new Decoder<Request>(Request.class));
					ch.pipeline().addLast(new NettyChannelHandler());
				}
			});

			ChannelFuture future = bootstrap.bind(url.getPort()).sync();
			System.out.println("listening port on " + url.getPort());

			future.channel().closeFuture().sync();
		} catch (Exception ce) {
			throw new RpcException("server start exception.", ce);
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	private class NettyChannelHandler extends SimpleChannelInboundHandler<Request> {
		@Override
		protected void channelRead0(ChannelHandlerContext ctx, Request request) throws Exception {
			Response response = serverHandler.received(request);
			ctx.writeAndFlush(response);
		}
	}

	@SuppressWarnings("rawtypes")
	private class Encoder extends MessageToByteEncoder {
		@Override
		protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
			out.writeBytes(serialization.serialize(msg));
		}
	}
	
	private class Decoder<T> extends ByteToMessageDecoder {
		private Class<T> clazz;
		public Decoder(Class<T> clazz) {
			this.clazz = clazz;
		}
		@Override
		protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> list) throws Exception {
			byte[] bytes = new byte[buf.readableBytes()];
			buf.readBytes(bytes);
			list.add(serialization.deserialize(bytes, clazz));
		}
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
	}
}
