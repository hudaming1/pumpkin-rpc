package org.hum.pumpkin.test.serialization.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.hum.pumpkin.test.serialization.model.EmployeeModel;
import org.hum.pumpkin.test.serialization.test.AbstractSerializationTest.Result;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.pool.KryoFactory;
import com.esotericsoftware.kryo.pool.KryoPool;

public class KryoSerializationTest {

	private static final KryoPool pool = new KryoPool.Builder(new KryoFactory() {
		public Kryo create() {
			return new Kryo();
		}
	}).build();
	static final Kryo kryo = pool.borrow();

	public static void main(String[] args) throws IOException {
		Result result = AbstractSerializationTest.test(new AbstractSerializationTest() {
			@Override
			public byte[] serialize(Object object) {
				try {
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					Output output = new Output(bos);
					kryo.writeObject(output, object);
					output.flush();
					if (kryo != null) {
						pool.release(kryo);
					}
					System.out.println(Arrays.toString(bos.toByteArray()));
					return bos.toByteArray();
				} catch (Exception ce) {
					ce.printStackTrace();
					return null;
				}
			}
		});
		System.out.println(result);
		byte[] bytes = new byte[] { 1, 1, 50, 48, 49, 57, 45, 48, 50, 45, 50, -74, 1, 69, 77, 80, 49, 53, 53, 49, 49, 55, 48, 53, 56, 56, 55, 52, -71, 1, -102, -63, -58, -113, -91, 90, 1, 122, 104, 97, 110, 103, 115, 97, -18, 1, 64, -77, -120, 0, 0, 0, 0, 0, 1, 0, 0 };
//								[1, 1, 50, 48, 49, 57, 45, 48, 50, 45, 50, -74, 1, 69, 77, 80, 49, 53, 53, 49, 49, 55, 48, 53, 56, 56, 55, 52, -71, 1, -102, -63, -58, -113, -91, 90, 1, 122, 104, 97, 110, 103, 115, 97, -18
//								[1, 1, 50, 48, 49, 57, 45, 48, 50, 45, 50, -74, 1, 69, 77, 80, 49, 53, 53, 49, 49, 55, 48, 53, 56, 56, 55, 52, -71, 1, -102, -63, -58, -113, -91, 90, 1, 122, 104, 97, 110, 103, 115, 97, -18, 1, 64, -77, -120, 0, 0, 0, 0, 0
//		name在前birthday在后		[1, 1, 50, 48, 49, 57, 45, 48, 50, 45, 50, -74, 1, 69, 77, 80, 49, 53, 53, 49, 49, 55, 48, 53, 56, 56, 55, 52, -71, 1, -102, -63, -58, -113, -91, 90, 1, 122, 104, 97, 110, 103, 115, 97, -18, 1, 64, -77, -120, 0, 0, 0, 0, 0, 1, 0, 0]
//		birthday在前name在后		[1, 1, 50, 48, 49, 57, 45, 48, 50, 45, 50, -74, 1, 69, 77, 80, 49, 53, 53, 49, 49, 55, 48, 53, 56, 56, 55, 52, -71, 1, -102, -63, -58, -113, -91, 90, 1, 122, 104, 97, 110, 103, 115, 97, -18, 1, 64, -77, -120, 0, 0, 0, 0, 0, 1, 0, 0]
		System.out.println(new String(bytes));
		Input input = new Input(bytes);
		EmployeeModel readObject = kryo.readObject(input, EmployeeModel.class);
		System.out.println(readObject);
	}
}
