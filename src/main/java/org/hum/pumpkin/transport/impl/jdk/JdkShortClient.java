package org.hum.pumpkin.transport.impl.jdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.hum.pumpkin.common.exception.RpcException;
import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.serialization.Serialization;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.transport.Client;
import org.hum.pumpkin.transport.message.Message;
import org.hum.pumpkin.transport.message.MessageBack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdkShortClient implements Client {

	private static final Logger logger = LoggerFactory.getLogger(JdkShortClient.class);

	private final Serialization serialization = ServiceLoaderHolder.loadByCache(Serialization.class);
	private String url;
	private int port;
	private Socket socket = null;
	private InputStream inputStream = null;
	private OutputStream outputStream = null;

	public JdkShortClient(URL url) throws UnknownHostException, IOException {
		this.url = url.getHost();
		this.port = url.getPort();
	}

	@Override
	public MessageBack send(Message message) {
		try {
			socket = new Socket(url, port);
			outputStream = socket.getOutputStream();
			
			serialization.serialize(outputStream, message);
			inputStream = socket.getInputStream();
			return serialization.deserialize(inputStream, MessageBack.class);
		} catch (IOException e) {
			throw new RpcException("invoke" + url + ":" + port + " exception", e);
		} finally {
			close();
		}
	}

	@Override
	public void close() {
		if (inputStream != null) {
			try {
				inputStream.close();
				inputStream = null;
			} catch (IOException e) {
				logger.error("close tcp [" + url + ":" + port + "] inputstream error", e);
			}
		}
		if (outputStream != null) {
			try {
				outputStream.close();
				outputStream = null;
			} catch (IOException e) {
				logger.error("close tcp [" + url + ":" + port + "] outputstream error", e);
			}
		}
		if (socket != null) {
			try {
				socket.close();
				socket = null;
			} catch (IOException e) {
				logger.error("close tcp [" + url + ":" + port + "] socket connection error", e);
			}
		}
	}
}
