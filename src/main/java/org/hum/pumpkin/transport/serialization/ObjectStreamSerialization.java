package org.hum.pumpkin.transport.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unchecked")
public class ObjectStreamSerialization implements Serialization {

	private static final Logger logger = LoggerFactory.getLogger(ObjectStreamSerialization.class);

	@Override
	public byte[] serialize(Object object) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			return baos.toByteArray();
		} catch (IOException e) {
			logger.error("serialize occured exception", e);
			return null;
		}
	}

	@Override
	public <T> T deserialize(byte[] bytes) {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (T) ois.readObject();
		} catch (Exception e) {
			logger.error("deserialize occured exception", e);
			return null;
		}
	}

	@Override
	public <T> T deserialize(InputStream inputStream) {
		try {
			ObjectInputStream ois = new ObjectInputStream(inputStream);
			return (T) ois.readObject();
		} catch (Exception e) {
			logger.error("deserialize occured exception", e);
			return null;
		}
	}

	@Override
	public <T> T deserialize(byte[] bytes, Class<T> clazz) {
		return deserialize(bytes);
	}
}
