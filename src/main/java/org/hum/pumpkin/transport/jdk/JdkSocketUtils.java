package org.hum.pumpkin.transport.jdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdkSocketUtils {

	private static final Logger logger = LoggerFactory.getLogger(JdkSocketUtils.class);

	public static void closeSocket(InputStream inputStream, OutputStream outputStream, Socket socket) {
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				logger.error("occured excepiton when close inputstream.", e);
			}
		}
		if (outputStream != null) {
			try {
				outputStream.close();
			} catch (IOException e) {
				logger.error("occured excepiton when close outputstream.", e);
			}
		}
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				logger.error("occured excepiton when close socket.", e);
			}
		}
	}
}
