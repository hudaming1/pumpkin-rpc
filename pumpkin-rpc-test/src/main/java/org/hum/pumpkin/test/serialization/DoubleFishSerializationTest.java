package org.hum.pumpkin.test.serialization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hum.pumpkin.test.serialization.AbstractSerializationTest.Result;
import org.hum.pumpkin.test.serialization.doublefish.DoubleFishSerializationOutput;

public class DoubleFishSerializationTest {

	public static void main(String[] args) throws IOException {
		Result result = AbstractSerializationTest.test(new AbstractSerializationTest() {
			@Override
			public byte[] serialize(Object object) {
				final ByteArrayOutputStream bos = new ByteArrayOutputStream();
				final DoubleFishSerializationOutput dfsOutput = new DoubleFishSerializationOutput(bos);
				try {
					dfsOutput.writeObject(object);
				} catch (IOException | IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				return bos.toByteArray();
			}
		});
		System.out.println(result);
	}
}
