package org.hum.pumpkin.transport.impl.netty;

import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;
import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.transport.Client;
import org.hum.pumpkin.transport.serialization.Serialization;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient implements Client {

	private URL url;
	private final Serialization serialization = ServiceLoaderHolder.loadByCache(Serialization.class);
	private NettyClientHandler nettyClientHandler = new NettyClientHandler();

	public NettyClient(URL url) {
		this.url = url;
		
		initClient();
	}
	
	private void initClient() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				EventLoopGroup group = new NioEventLoopGroup();
				try {
					Bootstrap bootstrap = new Bootstrap();
					bootstrap.group(group);
					bootstrap.channel(NioSocketChannel.class);
					bootstrap.handler(new ChannelInitializer<Channel>() {
						@Override
						protected void initChannel(Channel ch) throws Exception {
							ch.pipeline().addLast(new NettyEncoder(serialization));
							ch.pipeline().addLast(new NettyDecoder<Response>(Response.class, serialization));
							ch.pipeline().addLast(nettyClientHandler);
						}
					});

					ChannelFuture future = bootstrap.connect(url.getHost(), url.getPort()).sync();
					future.channel().closeFuture().sync();
				} catch (Exception ce) {
					ce.printStackTrace();
				} finally {
					group.shutdownGracefully();
				}
			}
		}).start();
	}

	@Override
	public Response send(Request request) {
		return nettyClientHandler.send(request);
	}

	@Override
	public void close() {
		// TODO
	}
}
