package org.hum.pumpkin.transport.impl.jdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;
import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.serialization.Serialization;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.transport.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdkKeepAliveClient implements Client {

	private static final Logger logger = LoggerFactory.getLogger(JdkKeepAliveClient.class);

	private final Serialization serialization = ServiceLoaderHolder.loadByCache(Serialization.class);

	private Socket socket;
	private InputStream inputStream;
	private OutputStream outputStream;
	private String host;
	private int port;

	public JdkKeepAliveClient(URL url) throws UnknownHostException, IOException {
		this.host = url.getHost();
		this.port = url.getPort();
		socket = new Socket(host, port);
		logger.info("socket " + host + ":" + port + " build connection success.");
		socket.setKeepAlive(true);
		outputStream = socket.getOutputStream();
		inputStream = socket.getInputStream();
	}

	@Override
	public Response send(Request request) {
		serialization.serialize(outputStream, request);
		return serialization.deserialize(inputStream, Response.class);
	}

	@Override
	public void close() {
		if (inputStream != null) {
			try {
				inputStream.close();
				inputStream = null;
			} catch (IOException e) {
				logger.error("close tcp [" + host + ":" + port + "] inputstream error", e);
			}
		}
		if (outputStream != null) {
			try {
				outputStream.close();
				outputStream = null;
			} catch (IOException e) {
				logger.error("close tcp [" + host + ":" + port + "] outputstream error", e);
			}
		}
		if (socket != null) {
			try {
				socket.close();
				socket = null;
			} catch (IOException e) {
				logger.error("close tcp [" + host + ":" + port + "] socket connection error", e);
			}
		}
	}
}
