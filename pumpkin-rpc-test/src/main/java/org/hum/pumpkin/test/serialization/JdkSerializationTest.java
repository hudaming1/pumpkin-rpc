package org.hum.pumpkin.test.serialization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.hum.pumpkin.test.serialization.AbstractSerializationTest.Result;

public class JdkSerializationTest {

	public static void main(String[] args) throws IOException {
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		final ObjectOutputStream oos = new ObjectOutputStream(bos);
		
		Result result = AbstractSerializationTest.test(new AbstractSerializationTest() {
			@Override
			public byte[] serialize(Object object) {
				try {
					oos.writeObject(object);
					oos.flush();
					oos.close();
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
