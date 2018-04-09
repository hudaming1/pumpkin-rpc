package org.hum.pumpkin.transport.impl.netty.server;

import org.hum.pumpkin.common.exception.PumpkinException;
import org.hum.pumpkin.common.exception.RpcException;
import org.hum.pumpkin.common.serviceloader.ExtensionLoader;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.common.url.URLConstant;
import org.hum.pumpkin.logger.Logger;
import org.hum.pumpkin.logger.LoggerFactory;
import org.hum.pumpkin.serialization.Serialization;
import org.hum.pumpkin.transport.impl.netty.codec.NettyDecoder;
import org.hum.pumpkin.transport.impl.netty.codec.NettyEncoder;
import org.hum.pumpkin.transport.impl.netty.support.NettyUtils;
import org.hum.pumpkin.transport.message.Message;
import org.hum.pumpkin.transport.server.AbstractServer;
import org.hum.pumpkin.transport.server.ServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * NettyServer2继承自AbstractServer
 */
public class NettyServer extends AbstractServer {

	private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);
	private URL url;
	private ServerHandler serverHandler;
	private final Serialization serialization;
	// TODO 这里的线程池数量肯定会和连接数成正比，需要改进
	private EventLoopGroup bossGroup = null;
	private EventLoopGroup workerGroup = null;
	private ServerBootstrap bootstrap = null;

	public NettyServer(URL url, ServerHandler serverHandler) {
		super(url, serverHandler);
		this.url = url;
		this.serverHandler = serverHandler;
		this.serialization = ExtensionLoader.getExtensionLoader(Serialization.class).get(url.getString(URLConstant.SERIALIZATION));
	}

	@Override
	public void initServer(URL url) throws PumpkinException {

		bossGroup = initEventLoopGroup();
		workerGroup = initEventLoopGroup();
		bootstrap = new ServerBootstrap();
		
		bootstrap.group(bossGroup, workerGroup);
		bootstrap.channel(NettyUtils.isSupportNativeET() ? EpollServerSocketChannel.class : NioServerSocketChannel.class);
		bootstrap.childHandler(new ChannelInitializer<Channel>() {
			@Override
			protected void initChannel(Channel ch) throws Exception {
				ch.pipeline().addLast(new NettyEncoder(serialization));
				ch.pipeline().addLast(new NettyDecoder<Message>(Message.class, serialization));
				ch.pipeline().addLast(new NettyServerHandler(serverHandler));
			}
		});
	}
	
	// 如果程序运行在Linux OS上，则请优先考虑使用Epoll模型，其性能要高于JDK的NIO实现（摘自《Netty In Action》4.3.2）
	private EventLoopGroup initEventLoopGroup() {
		return NettyUtils.isSupportNativeET() ? new EpollEventLoopGroup() : new NioEventLoopGroup();
	}

	@Override
	public void openPort() {
		try {
			ChannelFuture future = bootstrap.bind(url.getPort()).sync();
			logger.info("netty server listening port on " + url.getPort());
			future.channel().closeFuture().sync();
		} catch (Exception ce) {
			throw new RpcException("open server exception.", ce);
		} finally {
			close();
		}
	}

	@Override
	public void doClose() {
		if (!bossGroup.isTerminated()) {
			bossGroup.shutdownGracefully();
		}
		if (workerGroup.isTerminated()) {
			workerGroup.shutdownGracefully();
		}
	}
}
