package org.hum.pumpkin.transport.impl.jdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.hum.pumpkin.common.RpcException;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.transport.Transporter;
import org.hum.pumpkin.transport.config.TransporterConfig;
import org.hum.pumpkin.transport.serialization.Serialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdkShortTransporter implements Transporter {

	private static final Logger logger = LoggerFactory.getLogger(JdkTransporterFactory.class);

	private final Serialization serialization = ServiceLoaderHolder.loadByCache(Serialization.class);
	private String address;
	private int port;

	public JdkShortTransporter(TransporterConfig config) throws UnknownHostException, IOException {
		this.address = config.getAddress();
		this.port = config.getPort();
	}

	@Override
	public Object invoke(Object invocation) {
		Socket socket = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			socket = new Socket(address, port);
			outputStream = socket.getOutputStream();
			
			outputStream.write(serialization.serialize(invocation));
			outputStream.flush();

			inputStream = socket.getInputStream();
			return serialization.deserialize(inputStream);
		} catch (IOException e) {
			throw new RpcException("invoke" + address + ":" + port + " exception", e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
					inputStream = null;
				} catch (IOException e) {
					logger.error("close tcp [" + address + ":" + port + "] inputstream error", e);
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
					outputStream = null;
				} catch (IOException e) {
					logger.error("close tcp [" + address + ":" + port + "] outputstream error", e);
				}
			}
			if (socket != null) {
				try {
					socket.close();
					socket = null;
				} catch (IOException e) {
					logger.error("close tcp [" + address + ":" + port + "] socket connection error", e);
				}
			}
		}
	}

	@Override
	public void close() {
	}
}
