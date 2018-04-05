package org.hum;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class BIOServerTest {

	public static void main(String[] args) throws IOException {
		final ServerSocket server = new ServerSocket(9080);
		final ExecutorService executorService = Executors.newFixedThreadPool(5, new ThreadFactory() {
			private final AtomicInteger id = new AtomicInteger(0);

			@Override
			public Thread newThread(Runnable r) {
				return new Thread(r, "thread-pool-" + id.getAndIncrement());
			}
		});
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("server start");
					while (true) {
						final Socket socket = server.accept();
						System.out.println("server accept client request");
						executorService.execute(new Runnable() {
							@Override
							public void run() {
								try {
									// in
									InputStream inputStream = socket.getInputStream();
									ObjectInputStream ois = new ObjectInputStream(inputStream);
									String readUTF = ois.readUTF();
									// process
									String result = readUTF + "huming";
									// out
									OutputStream outputStream = socket.getOutputStream();
									ObjectOutputStream oos = new ObjectOutputStream(outputStream);
									oos.writeUTF(result);
									oos.flush();
									// finally
									ois.close();
									oos.close();
									socket.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						});
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();

		System.in.read();
		server.close();
	}
}
