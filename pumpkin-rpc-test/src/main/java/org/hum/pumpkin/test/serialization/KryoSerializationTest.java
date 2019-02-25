package org.hum.pumpkin.test.serialization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hum.pumpkin.test.serialization.AbstractSerializationTest.Result;
import org.hum.pumpkin.test.serialization.model.EmployeeModel;

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
					return bos.toByteArray();
				} catch (Exception ce) {
					ce.printStackTrace();
					return null;
				}
			}
		});
		System.out.println(result);
		byte[] bytes = new byte[] { 1, 1, 69, 77, 80, 49, 53, 53, 49, 48, 57, 49, 49, 50, 50, 50, 49, -79, 1, -58, -128, -30, -61, -92, 90, 1, 122, 104, 97, 110, 103, 115, 97, -18, 1, 64, -77, -120, 0, 0, 0, 0, 0, 1, 0, 0 };
		Input input = new Input(bytes);
		EmployeeModel readObject = kryo.readObject(input, EmployeeModel.class);
		System.out.println(readObject);
	}
}
