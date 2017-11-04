package org.hum.pumpkin.transport.impl.jdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.hum.pumpkin.common.RpcException;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.transport.Transporter;
import org.hum.pumpkin.transport.config.TransporterConfig;
import org.hum.pumpkin.transport.factory.AbstractTransporterFactory;
import org.hum.pumpkin.transport.serialization.Serialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdkTransporterFactory extends AbstractTransporterFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(JdkTransporterFactory.class);
	
	private final Serialization serialization = ServiceLoaderHolder.loadByCache(Serialization.class);
	
	@Override
	protected Transporter doCreate(final TransporterConfig config) {
		try {
			return new Transporter() {
				
				private Socket socket;
				private InputStream inputStream;
				private OutputStream outputStream;
				private String address;
				private int port;
				{
					this.address = config.getAddress();
					this.port = config.getPort();
					socket = new Socket(address, port); 
					logger.info("socket " + address + ":" + port + " build connection success.");
					if (config.isKeepAlive()) {
						socket.setKeepAlive(true); 
					}
					outputStream = socket.getOutputStream();
				}
				
				@Override
				public Object invoke(Object invocation) {
					try {
						byte[] bytes = serialization.serialize(invocation);
						outputStream.write(bytes);
						outputStream.flush();
						
						inputStream = socket.getInputStream();
						return serialization.deserialize(inputStream);
					} catch (IOException e) {
						throw new RpcException("invoke" + address + ":" + port  + " exception", e);
					}
				}

				@Override
				public void close() {
					if (inputStream != null) {
						try {
							inputStream.close();
						} catch (IOException e) {
							logger.error("close tcp [" + address + ":" + port + "] inputstream error", e);
						}
					}
					if (outputStream != null) {
						try {
							outputStream.close();
						} catch (IOException e) {
							logger.error("close tcp [" + address + ":" + port + "] outputstream error", e);
						}
					}
					if (socket != null) {
						try {
							socket.close();
						} catch (IOException e) {
							logger.error("close tcp [" + address + ":" + port + "] socket connection error", e);
						}
					}
				}
			};
		} catch (IOException e) {
			throw new RpcException("create jdk transporter[" + config.getAddress() + ":" + config.getPort() + "] error", e);
		}
	}
}
