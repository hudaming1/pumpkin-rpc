package org.hum.pumpkin.test.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.hum.pumpkin.test.serialization.AbstractSerializationTest.Result;

import com.caucho.hessian.io.HessianInput;
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
					System.out.println(Arrays.toString(bos.toByteArray()));
					return bos.toByteArray();
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
		});
		System.out.println(result);
		byte[] bytes = new byte[] { 77, 116, 0, 54, 111, 114, 103, 46, 104, 117, 109, 46, 112, 117, 109, 112, 107, 105, 110, 46, 116, 101, 115, 116, 46, 115, 101, 114, 105, 97, 108, 105, 122, 97, 116, 105, 111, 110, 46, 109, 111, 100, 101, 108, 46, 69, 109, 112, 108, 111, 121, 101, 101, 77, 111, 100, 101, 108, 83, 0, 10, 101, 109, 112, 108, 111, 121, 101, 101, 78, 111, 83, 0, 16, 69, 77, 80, 49, 53, 53, 49, 48, 57, 49, 55, 57, 52, 55, 50, 49, 83, 0, 6, 115, 97, 108, 97, 114, 121, 68, 64, -77, -120, 0, 0, 0, 0, 0, 83, 0, 2, 105, 100, 76, 0, 0, 1, 105, 36, 70, -125, 33, 83, 0, 4, 110, 97, 109, 101, 83, 0, 8, 122, 104, 97, 110, 103, 115, 97, 110, 83, 0, 3, 115, 101, 120, 70, 83, 0, 5, 116, 97, 115, 107, 115, 78, 122 };
		HessianInput input = new HessianInput(new ByteArrayInputStream(bytes));
		System.out.println(input.readObject());
	}
}
