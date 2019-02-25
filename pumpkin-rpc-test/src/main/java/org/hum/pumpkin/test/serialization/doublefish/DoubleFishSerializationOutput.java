package org.hum.pumpkin.test.serialization.doublefish;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.hum.pumpkin.test.serialization.doublefish.implement.AbstractOutput;

public class DoubleFishSerializationOutput {

	private DataOutputStream outputStream;
	private static final int MAGIC_NUMBER = 20190225;

	public DoubleFishSerializationOutput(OutputStream outputStream) {
		this.outputStream = new DataOutputStream(outputStream);
	}

	public void writeObject(Object object) throws IllegalArgumentException, IllegalAccessException, IOException {
		if (object == null) {
			throw new IllegalArgumentException("object cannot be null.");
		} else if (!Serializable.class.isAssignableFrom(object.getClass())) {
			throw new IllegalArgumentException("object must be implements class-type[java.io.Serializabe]");
		}
		// 1.write magic_number
		outputStream.writeInt(MAGIC_NUMBER);
		
		// 2.write all fields
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName());
			if (Modifier.isTransient(field.getModifiers()) || Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
				continue;
			}
			field.setAccessible(true);
			AbstractOutput.get(field.getType()).write(outputStream, field.get(object));
		}
	}
}
