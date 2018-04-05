package org.hum.pumpkin.serialization.jdknative;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.hum.pumpkin.logger.Logger;
import org.hum.pumpkin.logger.LoggerFactory;
import org.hum.pumpkin.serialization.Serialization;

@SuppressWarnings("unchecked")
public class ObjectStreamSerialization implements Serialization {

	private static final Logger logger = LoggerFactory.getLogger(ObjectStreamSerialization.class);

	@Override
	public void serialize(OutputStream outputStream, Object object) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			outputStream.write(baos.toByteArray());
			outputStream.flush();
		} catch (IOException e) {
			logger.error("serialize occured exception", e);
		}
	}

	@Override
	public <T> T deserialize(InputStream inputStream, Class<T> clazz) {
		try {
			ObjectInputStream ois = new ObjectInputStream(inputStream);
			return (T) ois.readObject();
		} catch (Exception e) {
			logger.error("deserialize occured exception", e);
			return null;
		}
	}
}
