package org.hum.pumpkin.transport.impl.jdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.hum.pumpkin.common.exception.RpcException;
import org.hum.pumpkin.common.serviceloader.ExtensionLoader;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.common.url.URLConstant;
import org.hum.pumpkin.logger.Logger;
import org.hum.pumpkin.logger.LoggerFactory;
import org.hum.pumpkin.serialization.Serialization;
import org.hum.pumpkin.transport.client.Client;
import org.hum.pumpkin.transport.message.Message;
import org.hum.pumpkin.transport.message.MessageBack;

public class JdkShortClient implements Client {

	private static final Logger logger = LoggerFactory.getLogger(JdkShortClient.class);

	private Serialization serialization;
	private String host;
	private URL url;
	private int port;
	private Socket socket = null;
	private InputStream inputStream = null;
	private OutputStream outputStream = null;

	public JdkShortClient(URL url) throws UnknownHostException, IOException {
		this.host = url.getHost();
		this.port = url.getPort();
		this.url = url;
		this.serialization = ExtensionLoader.getExtensionLoader(Serialization.class).get(url.getString(URLConstant.SERIALIZATION));
	}

	@Override
	public MessageBack send(Message message) {
		try {
			socket = new Socket(host, port);
			outputStream = socket.getOutputStream();
			serialization.serialize(outputStream, message);
			inputStream = socket.getInputStream();
			return serialization.deserialize(inputStream, MessageBack.class);
		} catch (IOException e) {
			throw new RpcException("invoke" + host + ":" + port + " exception", e);
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

	@Override
	public URL getURL() {
		return url;
	}
}
