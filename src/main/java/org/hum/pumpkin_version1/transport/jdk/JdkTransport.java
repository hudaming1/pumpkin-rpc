package org.hum.pumpkin_version1.transport.jdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

import org.hum.pumpkin_version1.serialize.Serialization;
import org.hum.pumpkin_version1.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin_version1.transport.AbstractTransporter;
import org.hum.pumpkin_version1.transport.bean.RpcInvocation;
import org.hum.pumpkin_version1.transport.bean.RpcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdkTransport extends AbstractTransporter {

	private static final Logger logger = LoggerFactory.getLogger(JdkTransport.class);

	private Serialization serialization = ServiceLoaderHolder.loadByCache(Serialization.class);
	
	private ServerSocket server;
	
	public void doOpen(int port) {
		try {
			server = new ServerSocket(port);
			isRun = true;
			logger.info("Jdk Tcp Server start, listening port on:" + port);
		} catch (BindException e) {
			logger.error("server port [" + port + "] already in used. ", e);
			return ;
		} catch (IOException e) {
			logger.error("server start occured excepiton", e);
			return;
		}
		listenning(server);
	}
	
	private void listenning(final ServerSocket server) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (isRun) {
					Socket socket = null;
					InputStream inputStream = null;
					OutputStream outputStream = null;
					try {
						socket = server.accept();
						inputStream = socket.getInputStream();
						outputStream = socket.getOutputStream();
						
						RpcInvocation invocation = serialization.deserialize(inputStream);

						RpcResult result = handler(invocation);
						
						outputStream.write(serialization.serialize(result));
						outputStream.flush();

					} catch (Exception e) {
						logger.error("server listening exception", e);
					} finally {
						// TODO keepalive
						JdkSocketUtils.closeSocket(inputStream, outputStream, socket);
					}
				}
			}
		}).start();
	}

	@Override
	public RpcResult invoke(RpcInvocation invocation) {
		return null;
	}
}
