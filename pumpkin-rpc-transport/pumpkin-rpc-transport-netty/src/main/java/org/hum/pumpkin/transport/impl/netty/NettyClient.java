package org.hum.pumpkin.transport.impl.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.hum.pumpkin.common.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.serialization.Serialization;
import org.hum.pumpkin.transport.Client;
import org.hum.pumpkin.transport.impl.netty.plugins.HeartBeatClientHandler;
import org.hum.pumpkin.transport.message.Message;
import org.hum.pumpkin.transport.message.MessageBack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyClient implements Client {

	private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);
	private URL url;
	private final Serialization serialization = ServiceLoaderHolder.loadByCache(Serialization.class);
	private NettyClientHandler nettyClientHandler = new NettyClientHandler();
	private EventLoopGroup group = null;
	private HeartBeatClientHandler heartBeatHandler = new HeartBeatClientHandler();

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
							ch.pipeline().addLast(new NettyDecoder<MessageBack>(MessageBack.class, serialization));
							ch.pipeline().addLast(heartBeatHandler);
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
		heartBeatHandler.waitHandShake();
	}

	@Override
	public MessageBack send(Message essage) {
		return nettyClientHandler.send(essage);
	}

	@Override
	public void close() {
		if (group.isTerminated()) {
			group.shutdownGracefully();
		}
	}

	@Override
	public URL getURL() {
		return url;
	}
}
