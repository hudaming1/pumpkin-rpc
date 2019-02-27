package org.hum.pumpkin.test.serialization.doublefish.implement;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class AbstractSerialization<T> {

	public abstract T read(DataInputStream dataInputStream, Class<T> classType) throws IOException;
	
	public abstract void write(DataOutputStream outputStream, Object obj) throws IOException;
	
	public static <T> AbstractSerialization<?> get(Class<T> classType) {
		if (classType == null) {
			throw new IllegalArgumentException("classType mustn't be null");
		}
		if (Integer.class.isAssignableFrom(classType)) {
			return new BasicTypeSerialization.IntegerOutput();
		} else if (long.class.isAssignableFrom(classType)) {
			return new BasicTypeSerialization.LongOutput();
		} else if (String.class.isAssignableFrom(classType)) {
			return new BasicTypeSerialization.StringOutput();
		} else if (Double.class.isAssignableFrom(classType)) {
			return new BasicTypeSerialization.DoubleOutput();
		} else {
			throw new IllegalArgumentException("unknown type[" + classType.getName() + "]");
		}
	}
}
