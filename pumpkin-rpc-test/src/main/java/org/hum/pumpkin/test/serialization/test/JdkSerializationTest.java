package org.hum.pumpkin.test.serialization.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.hum.pumpkin.test.serialization.test.AbstractSerializationTest.Result;

public class JdkSerializationTest {

	public static void main(String[] args) throws IOException {
		
		Result result = AbstractSerializationTest.test(new AbstractSerializationTest() {
			@Override
			public byte[] serialize(Object object) {
				try {
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(bos);
					oos.writeObject(object);
					oos.flush();
					oos.close();
					System.out.println(new String(bos.toByteArray()));
					return bos.toByteArray();
				} catch (Exception ce) {
					ce.printStackTrace();
					return null;
				}
			}
		});
		System.out.println(result);
	}
}
