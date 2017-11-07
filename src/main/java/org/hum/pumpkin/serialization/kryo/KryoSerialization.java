package org.hum.pumpkin.serialization.kryo;

import java.io.InputStream;
import java.io.OutputStream;

import org.hum.pumpkin.serialization.Serialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.pool.KryoFactory;
import com.esotericsoftware.kryo.pool.KryoPool;

public class KryoSerialization implements Serialization {

	private static final Logger logger = LoggerFactory.getLogger(KryoSerialization.class);

	private KryoFactory factory = new KryoFactory() {
		public Kryo create() {
			return new Kryo();
		}
	};

	private KryoPool pool = new KryoPool.Builder(factory).build();

	@Override
	public void serialize(OutputStream outputStream, Object object) {
		Kryo kryo = null;
		Output output = null;
		try {
			kryo = pool.borrow();
			output = new Output(outputStream);
			kryo.writeObject(output, object);
		} finally {
			if (output != null) {
				output.close();
			}
			if (kryo != null) {
				pool.release(kryo);
			}
		}
	}

	@Override
	public <T> T deserialize(InputStream inputStream, Class<T> clazz) {
		Input input = null;
		Kryo kryo = null;
		try {
			kryo = pool.borrow();
			input = new Input(inputStream);
			return kryo.readObject(input, clazz);
		} catch (Exception ce) {
			logger.error("deserialize " + clazz.getName() + " error", ce);
			return null;
		} finally {
			if (input != null) {
				input.close();
			}
			if (kryo != null) {
				pool.release(kryo);
			}
		}
	}
}