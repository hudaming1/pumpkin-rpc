package org.hum.pumpkin.transport.impl.netty;

import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;
import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.serialization.Serialization;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.transport.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient implements Client {

	private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);
	private URL url;
	private final Serialization serialization = ServiceLoaderHolder.loadByCache(Serialization.class);
	private NettyClientHandler nettyClientHandler = new NettyClientHandler();
	private EventLoopGroup group = null;

	public NettyClient(URL url) {
		this.url = url;
		
		initClient();
	}
	
	private void initClient() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				group = new NioEventLoopGroup();
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
					logger.error("NettyClient connect error", ce);
				} finally {
					close();
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
		if (group.isTerminated()) {
			group.shutdownGracefully();
		}
	}
}
