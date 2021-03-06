package org.hum.pumpkin.transport.impl.netty.client;

import org.hum.pumpkin.common.serviceloader.ExtensionLoader;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.common.url.URLConstant;
import org.hum.pumpkin.logger.Logger;
import org.hum.pumpkin.logger.LoggerFactory;
import org.hum.pumpkin.serialization.Serialization;
import org.hum.pumpkin.transport.client.Client;
import org.hum.pumpkin.transport.impl.netty.codec.NettyDecoder;
import org.hum.pumpkin.transport.impl.netty.codec.NettyEncoder;
import org.hum.pumpkin.transport.message.Message;
import org.hum.pumpkin.transport.message.MessageBack;

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
	private Serialization serialization;
	private NettyClientHandler nettyClientHandler = new NettyClientHandler();
	private EventLoopGroup group = null;
	// TODO 心跳放到exchange上吧
//	private HeartBeatClientHandler heartBeatHandler = new HeartBeatClientHandler();

	public NettyClient(URL url) {
		this.url = url;
		this.serialization = ExtensionLoader.getExtensionLoader(Serialization.class).get(url.getString(URLConstant.SERIALIZATION));
		initClient();
	}
	
	private void initClient() {
		try {
			group = new NioEventLoopGroup();
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.channel(NioSocketChannel.class).group(group);
			bootstrap.handler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					ch.pipeline().addLast(new NettyEncoder(serialization));
					ch.pipeline().addLast(new NettyDecoder<MessageBack>(MessageBack.class, serialization));
					// ch.pipeline().addLast(heartBeatHandler);
					ch.pipeline().addLast(nettyClientHandler);
				}
			});

			ChannelFuture future = bootstrap.connect(url.getHost(), url.getPort()).sync();
//			future.channel().closeFuture().sync();
		} catch (Exception ce) {
			logger.error("NettyClient connect error", ce);
		} finally {
			close();
		}
		// heartBeatHandler.waitHandShake();
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
