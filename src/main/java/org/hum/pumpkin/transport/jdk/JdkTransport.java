package org.hum.pumpkin.transport.jdk;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.hum.pumpkin.invoker.Invoker;
import org.hum.pumpkin.serialize.Serialization;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.threadpool.ThreadPoolFactory;
import org.hum.pumpkin.transport.AbstractTransporter;
import org.hum.pumpkin.transport.ServiceKey;
import org.hum.pumpkin.transport.bean.RpcInvocation;
import org.hum.pumpkin.transport.bean.RpcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdkTransport extends AbstractTransporter {

	private static final Logger logger = LoggerFactory.getLogger(JdkTransport.class);

	private Serialization serialization = ServiceLoaderHolder.loadByCache(Serialization.class);
	
	private ServerSocket server;
	
	public void doOpen(int port) {
		try {
			server = new ServerSocket(port);
			listenning(server);
		} catch (IOException e) {
			logger.error("server start occured excepiton", e);
		}
	}
	
	private void listenning(final ServerSocket server) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (isRun) {
					try {
						Socket socket = server.accept();
						final RpcInvocation invocation = parse2Invocation(socket.getInputStream());

						RpcResult result = handler(invocation);

					} catch (Exception e) {
						logger.error("server listening exception", e);
					}
				}
			}
		}).start();
	}
	
	private RpcInvocation parse2Invocation(InputStream inputStream) {
		
		
		
		Class clazz = null;
		String method = "";
		Class[] paramTypes = null;
		Object[] params = null;
		
		return new RpcInvocation(clazz, method, paramTypes, params);
	}

	public RpcResult invoke(RpcInvocation invocation) {
		return null;
	}
	
	static class A implements Serializable {
	}
	
	public static void main(String[] args) throws Exception {
		A a = new A();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(a);
		
		byte[] byteArray = bos.toByteArray();
		
		ByteArrayInputStream bis = new ByteArrayInputStream();
		ObjectInputStream ois = new ObjectInputStream(bis);
		
		System.out.println(ois.readObject());
	}
}
