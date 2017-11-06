package org.hum.pumpkin.transport.impl.jdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.hum.pumpkin.common.RpcException;
import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;
import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.transport.Transporter;
import org.hum.pumpkin.transport.serialization.Serialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdkKeeyAliveTranporter implements Transporter {

	private static final Logger logger = LoggerFactory.getLogger(JdkKeeyAliveTranporter.class);

	private final Serialization serialization = ServiceLoaderHolder.loadByCache(Serialization.class);
	
	private Socket socket;
	private InputStream inputStream;
	private OutputStream outputStream;
	private String host;
	private int port;

	public JdkKeeyAliveTranporter(URL url) throws UnknownHostException, IOException {
		this.host = url.getHost();
		this.port = url.getPort();
		socket = new Socket(host, port);
		logger.info("socket " + host + ":" + port + " build connection success.");
		socket.setKeepAlive(true);
		outputStream = socket.getOutputStream();
	}

	@Override
	public Response send(Request request) {
		try {
			outputStream.write(serialization.serialize(request));
			outputStream.flush();
			
			inputStream = socket.getInputStream();
			return serialization.deserialize(inputStream);
		} catch (IOException e) {
			throw new RpcException("invoke" + host + ":" + port + " exception", e);
		}
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
