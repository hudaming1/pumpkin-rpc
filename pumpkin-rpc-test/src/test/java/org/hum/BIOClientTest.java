package org.hum;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class BIOClientTest {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1", 9080);
		// out
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeUTF("haha ");
		oos.flush();
		// read
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		String readUTF = ois.readUTF();
		
		System.out.println(readUTF);
		
		ois.close();
		oos.close();
		socket.close();
	}
}
