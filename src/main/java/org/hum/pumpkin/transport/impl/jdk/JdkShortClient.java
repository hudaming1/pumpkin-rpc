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
import org.hum.pumpkin.transport.Client;
import org.hum.pumpkin.transport.serialization.Serialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdkShortClient implements Client {

	private static final Logger logger = LoggerFactory.getLogger(JdkShortClient.class);

	private final Serialization serialization = ServiceLoaderHolder.loadByCache(Serialization.class);
	private String url;
	private int port;

	public JdkShortClient(URL url) throws UnknownHostException, IOException {
		this.url = url.getHost();
		this.port = url.getPort();
	}

	@Override
	public Response send(Request request) {
		Socket socket = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			socket = new Socket(url, port);
			outputStream = socket.getOutputStream();
			
			outputStream.write(serialization.serialize(request));
			outputStream.flush();

			inputStream = socket.getInputStream();
			return serialization.deserialize(inputStream);
		} catch (IOException e) {
			throw new RpcException("invoke" + url + ":" + port + " exception", e);
		} finally {
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

	@Override
	public void close() {
	}
}
