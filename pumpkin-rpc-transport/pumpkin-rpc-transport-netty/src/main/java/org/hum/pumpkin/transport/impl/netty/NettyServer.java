package org.hum.pumpkin.transport.impl.netty;

import org.hum.pumpkin.common.exception.RpcException;
import org.hum.pumpkin.common.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.serialization.Serialization;
import org.hum.pumpkin.transport.event.ServerEventHandler;
import org.hum.pumpkin.transport.message.Message;
import org.hum.pumpkin.transport.server.Server;
import org.hum.pumpkin.transport.server.ServerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * NettyServer实现Server
 */
public class NettyServer implements Server {

	private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);
	private URL url;
	private ServerHandler serverHandler;
	private final Serialization serialization = ServiceLoaderHolder.loadByCache(Serialization.class);
	private EventLoopGroup bossGroup = null;
	private EventLoopGroup workerGroup = null;
	private ServerEventHandler serverEventHandler;

	public NettyServer(URL url, ServerHandler serverHandler) {
		this.url = url;
		this.serverHandler = serverHandler;
	}

	@Override
	public void open() {
		bossGroup = new NioEventLoopGroup();
		workerGroup = new NioEventLoopGroup();
		try {

			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.childHandler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					ch.pipeline().addLast(new NettyEncoder(serialization));
					ch.pipeline().addLast(new NettyDecoder<Message>(Message.class, serialization));
//					ch.pipeline().addLast(new HeartBeatServerHandler(authController));
					ch.pipeline().addLast(new NettyServerHandler(serverHandler));
				}
			});

			ChannelFuture future = bootstrap.bind(url.getPort()).sync();
			logger.info("netty server listening port on " + url.getPort());
			
			if (serverEventHandler != null) {
				serverEventHandler.fireOpen(serverHandler);
			}

			future.channel().closeFuture().sync();
		} catch (Exception ce) {
			throw new RpcException("server start exception.", ce);
		} finally {
			close();
		}
	}

	@Override
	public void close() {
		if (serverEventHandler != null) {
			serverEventHandler.fireClose(serverHandler);
		}
		if (!bossGroup.isTerminated()) {
			bossGroup.shutdownGracefully();
		}
		if (workerGroup.isTerminated()) {
			workerGroup.shutdownGracefully();
		}
	}
}
