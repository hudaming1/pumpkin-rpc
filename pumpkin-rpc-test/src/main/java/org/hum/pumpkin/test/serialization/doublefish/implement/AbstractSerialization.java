package org.hum.pumpkin.test.serialization.doublefish.implement;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author huming
 *
 * @param <T>
 */
public abstract class AbstractSerialization<T> {
	

	public static final byte NULL_VALUE = 0;
	public static final byte NOT_NULL_VALUE = 1;
	
	private static final ObjectSerialization objectSerialization = new ObjectSerialization<>();
	private static final BasicTypeSerialization.BooleanSerialization booleanSerialization = new BasicTypeSerialization.BooleanSerialization();
	private static final BasicTypeSerialization.CharSerialization charSerialization = new BasicTypeSerialization.CharSerialization();
	private static final BasicTypeSerialization.ShortSerialization shortSerialization = new BasicTypeSerialization.ShortSerialization();
	private static final BasicTypeSerialization.IntegerSerialization intSerialization = new BasicTypeSerialization.IntegerSerialization();
	private static final BasicTypeSerialization.LongSerialization longSerialization = new BasicTypeSerialization.LongSerialization();
	private static final BasicTypeSerialization.StringSerialization stringSerialization = new BasicTypeSerialization.StringSerialization();
	private static final BasicTypeSerialization.FloatSerialization floatSerialization = new BasicTypeSerialization.FloatSerialization();
	private static final BasicTypeSerialization.DoubleSerialization doubleSerialization = new BasicTypeSerialization.DoubleSerialization();
	private static final ArraySerialization.IntArraySerialization intArraySerialization = new ArraySerialization.IntArraySerialization();
	private static final ArraySerialization.IntegerArraySerialization integerArraySerialization = new ArraySerialization.IntegerArraySerialization();

	public abstract T read(DataInputStream dataInputStream, Class<?> classType) throws IOException;
	
	public abstract void write(DataOutputStream outputStream, Object obj) throws IOException;
	
	public static <T> AbstractSerialization<?> get(Class<T> classType) {
		if (classType == null) {
			throw new IllegalArgumentException("classType mustn't be null");
		}
		
		if (Integer.class.isAssignableFrom(classType) || int.class.isAssignableFrom(classType)) {
			return intSerialization;
		} else if (Short.class.isAssignableFrom(classType) || short.class.isAssignableFrom(classType)) {
			return shortSerialization;
		} else if (Character.class.isAssignableFrom(classType) || char.class.isAssignableFrom(classType)) {
			return charSerialization;
		} else if (Boolean.class.isAssignableFrom(classType) || boolean.class.isAssignableFrom(classType)) {
			return booleanSerialization;
		} else if (Long.class.isAssignableFrom(classType) || long.class.isAssignableFrom(classType)) {
			return longSerialization;
		} else if (Float.class.isAssignableFrom(classType) || float.class.isAssignableFrom(classType)) {
			return floatSerialization;
		} else if (Double.class.isAssignableFrom(classType) || double.class.isAssignableFrom(classType)) {
			return doubleSerialization;
		} else if (String.class.isAssignableFrom(classType)) {
			return stringSerialization;
		} else if (int[].class.isAssignableFrom(classType)) {
			return intArraySerialization;
		} else if (Integer[].class.isAssignableFrom(classType)) {
			return integerArraySerialization;
		} else if (List.class.isAssignableFrom(classType)) {
			throw new UnsupportedOperationException();
		} else if (Set.class.isAssignableFrom(classType)) {
			throw new UnsupportedOperationException();
		} else if (Collection.class.isAssignableFrom(classType)) {
			throw new UnsupportedOperationException();
		} else if (Object.class.isAssignableFrom(classType)) {
			return objectSerialization;
		} else {
			throw new IllegalArgumentException("unknown type[" + classType.getName() + "]");
		}
	}
}
