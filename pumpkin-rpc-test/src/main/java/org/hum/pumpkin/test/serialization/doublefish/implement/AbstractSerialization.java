package org.hum.pumpkin.test.serialization.doublefish.implement;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class AbstractSerialization<T> {
	
	private static final BasicTypeSerialization.IntegerOutput intOutput = new BasicTypeSerialization.IntegerOutput();
	private static final BasicTypeSerialization.LongOutput longOutput = new BasicTypeSerialization.LongOutput();
	private static final BasicTypeSerialization.StringOutput stringOutput = new BasicTypeSerialization.StringOutput();
	private static final BasicTypeSerialization.DoubleOutput doubleOutput = new BasicTypeSerialization.DoubleOutput();

	public abstract T read(DataInputStream dataInputStream, Class<?> classType) throws IOException;
	
	public abstract void write(DataOutputStream outputStream, Object obj) throws IOException;
	
	public static <T> AbstractSerialization<?> get(Class<T> classType) {
		if (classType == null) {
			throw new IllegalArgumentException("classType mustn't be null");
		}
		if (Integer.class.isAssignableFrom(classType) || int.class.isAssignableFrom(classType)) {
			return intOutput;
		} else if (Long.class.isAssignableFrom(classType) || long.class.isAssignableFrom(classType)) {
			return longOutput;
		} else if (String.class.isAssignableFrom(classType)) {
			return stringOutput;
		} else if (Double.class.isAssignableFrom(classType) || double.class.isAssignableFrom(classType)) {
			return doubleOutput;
		} else {
			throw new IllegalArgumentException("unknown type[" + classType.getName() + "]");
		}
	}
}
