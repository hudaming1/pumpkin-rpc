package org.hum.pumpkin.transport.impl.jdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.hum.pumpkin.common.RpcException;
import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;
import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.transport.ServerHandler;
import org.hum.pumpkin.transport.Server;
import org.hum.pumpkin.transport.serialization.Serialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdkServer implements Server {

	private static final Logger logger = LoggerFactory.getLogger(JdkServer.class);

	private final Serialization serialization = ServiceLoaderHolder.loadByCache(Serialization.class);

	private ServerHandler serverHandler;
	private volatile boolean isRun;
	private URL url;
	private ServerSocket server;

	public JdkServer(URL url, ServerHandler serverHandler) {
		this.url = url;
		this.serverHandler = serverHandler;
	}

	@Override
	public void open() {
		try {
			if (isRun) {
				return;
			}
			isRun = true;
			server = new ServerSocket(url.getPort());
			listenning();
		} catch (Exception e) {
			isRun = false;
			throw new RpcException("server[" + url.getPort() + "] start failed", e);
		}
	}

	private void listenning() {
		// TODO 这里新建线程好吗？
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (isRun) {
					try {
						Socket socket = server.accept();
						handler(socket);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private void handler(final Socket socket) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						InputStream inputStream = socket.getInputStream();
						Request request = serialization.deserialize(inputStream);
						Response response = serverHandler.received(request);
						byte[] bytes = serialization.serialize(response);
						OutputStream outputStream = socket.getOutputStream();
						outputStream.write(bytes);
						outputStream.flush();
					} catch (Exception ce) {
						logger.error("process client[" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "] exception", ce);
					}
				}
			}
		}).start();
	}

	@Override
	public void close() {
		if (!isRun) {
			return;
		}
		isRun = false;
		if (server != null) {
			try {
				server.close();
			} catch (IOException e) {
				logger.error("close tcp server [" + url.getHost() + ":" + url.getPort() + "] socket connection error",
						e);
			}
		}
	}
}