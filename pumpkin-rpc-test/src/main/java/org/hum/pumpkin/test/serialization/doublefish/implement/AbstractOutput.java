package org.hum.pumpkin.test.serialization.doublefish.implement;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class AbstractOutput {

	public abstract void write(DataOutputStream outputStream, Object obj) throws IOException;
	
	public static AbstractOutput get(Class<?> classType) {
		if (classType == null) {
			throw new IllegalArgumentException("classType mustn't be null");
		}
		if (Integer.class.isAssignableFrom(classType)) {
			return new BasicTypeOutput.IntegerOutput();
		} else if (long.class.isAssignableFrom(classType)) {
			return new BasicTypeOutput.LongOutput();
		} else if (String.class.isAssignableFrom(classType)) {
			return new BasicTypeOutput.StringOutput();
		} else if (Double.class.isAssignableFrom(classType)) {
			return new BasicTypeOutput.DoubleOutput();
		} else {
			throw new IllegalArgumentException("unknown type[" + classType.getName() + "]");
		}
	}
}
