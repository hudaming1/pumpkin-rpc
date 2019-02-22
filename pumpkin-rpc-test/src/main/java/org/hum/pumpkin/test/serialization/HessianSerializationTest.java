package org.hum.pumpkin.test.serialization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hum.pumpkin.test.serialization.AbstractSerializationTest.Result;

import com.caucho.hessian.io.HessianOutput;

public class HessianSerializationTest {

	public static void main(String[] args) throws IOException {
		Result result = AbstractSerializationTest.test(new AbstractSerializationTest() {
			@Override
			public byte[] serialize(Object object) {
				try {
					final ByteArrayOutputStream bos = new ByteArrayOutputStream();
					final HessianOutput ho = new HessianOutput(bos);
					ho.writeObject(object);
					return bos.toByteArray();
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
		});
		System.out.println(result);
	}
}
