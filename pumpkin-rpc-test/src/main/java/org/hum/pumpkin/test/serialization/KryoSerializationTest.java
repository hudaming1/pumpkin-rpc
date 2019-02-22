package org.hum.pumpkin.test.serialization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hum.pumpkin.test.serialization.AbstractSerializationTest.Result;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.pool.KryoFactory;
import com.esotericsoftware.kryo.pool.KryoPool;

public class KryoSerializationTest {

	private static final KryoPool pool = new KryoPool.Builder(new KryoFactory() {
		public Kryo create() {
			return new Kryo();
		}
	}).build();
	static final ByteArrayOutputStream bos = new ByteArrayOutputStream();
	static final Kryo kryo = pool.borrow();

	public static void main(String[] args) throws IOException {
		Result result = AbstractSerializationTest.test(new AbstractSerializationTest() {
			@Override
			public byte[] serialize(Object object) {
				try {
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
	}
}
