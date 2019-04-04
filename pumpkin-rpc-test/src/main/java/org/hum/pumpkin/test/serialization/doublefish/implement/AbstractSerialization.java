package org.hum.pumpkin.test.serialization.doublefish.implement;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author huming
 *
 * @param <T>
 */
public abstract class AbstractSerialization<T> {
	

	public static final byte NULL_VALUE = 0;
	public static final byte NOT_NULL_VALUE = 1;
	
	private static final ObjectSerialization<?> objectSerialization = new ObjectSerialization<>();
	private static final BasicTypeSerialization.BooleanSerialization booleanSerialization = new BasicTypeSerialization.BooleanSerialization();
	private static final BasicTypeSerialization.ByteSerialization byteSerialization = new BasicTypeSerialization.ByteSerialization();
	private static final BasicTypeSerialization.CharSerialization charSerialization = new BasicTypeSerialization.CharSerialization();
	private static final BasicTypeSerialization.ShortSerialization shortSerialization = new BasicTypeSerialization.ShortSerialization();
	private static final BasicTypeSerialization.IntegerSerialization intSerialization = new BasicTypeSerialization.IntegerSerialization();
	private static final BasicTypeSerialization.LongSerialization longSerialization = new BasicTypeSerialization.LongSerialization();
	private static final BasicTypeSerialization.StringSerialization stringSerialization = new BasicTypeSerialization.StringSerialization();
	private static final BasicTypeSerialization.FloatSerialization floatSerialization = new BasicTypeSerialization.FloatSerialization();
	private static final BasicTypeSerialization.DoubleSerialization doubleSerialization = new BasicTypeSerialization.DoubleSerialization();
	private static final ArraySerialization.IntArraySerialization intArraySerialization = new ArraySerialization.IntArraySerialization();
	private static final ArraySerialization.IntegerArraySerialization integerArraySerialization = new ArraySerialization.IntegerArraySerialization();
	private static final ArraySerialization.ShortArraySerialization shortArraySerialization = new ArraySerialization.ShortArraySerialization();
	private static final ArraySerialization.ShortWrapArraySerialization shortWrapArraySerialization = new ArraySerialization.ShortWrapArraySerialization();
	private static final ArraySerialization.LongArraySerialization longArraySerialization = new ArraySerialization.LongArraySerialization();
	private static final ArraySerialization.LongWrapArraySerialization longWrapArraySerialization = new ArraySerialization.LongWrapArraySerialization();
	private static final ArraySerialization.BooleanArraySerialization booleanArraySerialization = new ArraySerialization.BooleanArraySerialization();
	private static final ArraySerialization.BooleanWrapArraySerialization booleanWrapArraySerialization = new ArraySerialization.BooleanWrapArraySerialization();
	private static final ArraySerialization.CharArraySerialization charArraySerialization = new ArraySerialization.CharArraySerialization();
	private static final ArraySerialization.CharacterArraySerialization characterArraySerialization = new ArraySerialization.CharacterArraySerialization();
	private static final ArraySerialization.ByteArraySerialization byteArraySerialization = new ArraySerialization.ByteArraySerialization();
	private static final ArraySerialization.ByteWrapArraySerialization byteWrapArraySerialization = new ArraySerialization.ByteWrapArraySerialization();
	private static final ArraySerialization.FloatArraySerialization floatArraySerialization = new ArraySerialization.FloatArraySerialization();
	private static final ArraySerialization.FloatWrapArraySerialization floatWrapArraySerialization = new ArraySerialization.FloatWrapArraySerialization();
	private static final ArraySerialization.DoubleArraySerialization doubleArraySerialization = new ArraySerialization.DoubleArraySerialization();
	private static final ArraySerialization.DoubleWrapArraySerialization doubleWrapArraySerialization = new ArraySerialization.DoubleWrapArraySerialization();
	private static final CollectionSerialization<?> COLLECTION_SERIALIZATION = new CollectionSerialization<>();

	public abstract T read(DataInputStream dataInputStream, Class<T> classType) throws IOException;
	
	public abstract void write(DataOutputStream outputStream, T obj) throws IOException;
	
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
		} else if (Byte.class.isAssignableFrom(classType) || byte.class.isAssignableFrom(classType)) {
			return byteSerialization;
		} else if (String.class.isAssignableFrom(classType)) {
			return stringSerialization;
		} else if (int[].class.isAssignableFrom(classType)) {
			return intArraySerialization;
		} else if (Integer[].class.isAssignableFrom(classType)) {
			return integerArraySerialization;
		} else if (short[].class.isAssignableFrom(classType)) {
			return shortArraySerialization;
		} else if (Short[].class.isAssignableFrom(classType)) {
			return shortWrapArraySerialization;
		} else if (long[].class.isAssignableFrom(classType)) {
			return longArraySerialization;
		} else if (Long[].class.isAssignableFrom(classType)) {
			return longWrapArraySerialization;
		} else if (float[].class.isAssignableFrom(classType)) {
			return floatArraySerialization;
		} else if (Float[].class.isAssignableFrom(classType)) {
			return floatWrapArraySerialization;
		} else if (double[].class.isAssignableFrom(classType)) {
			return doubleArraySerialization;
		} else if (Double[].class.isAssignableFrom(classType)) {
			return doubleWrapArraySerialization;
		} else if (byte[].class.isAssignableFrom(classType)) {
			return byteArraySerialization;
		} else if (Byte[].class.isAssignableFrom(classType)) {
			return byteWrapArraySerialization;
		} else if (boolean[].class.isAssignableFrom(classType)) {
			return booleanArraySerialization;
		} else if (Boolean[].class.isAssignableFrom(classType)) {
			return booleanWrapArraySerialization;
		} else if (char[].class.isAssignableFrom(classType)) {
			return charArraySerialization;
		} else if (Character[].class.isAssignableFrom(classType)) {
			return characterArraySerialization;
		} else if (Set.class.isAssignableFrom(classType)) {
			throw new UnsupportedOperationException();
		} else if (Map.class.isAssignableFrom(classType)) {
			throw new UnsupportedOperationException();
		} else if (Collection.class.isAssignableFrom(classType)) {
			return COLLECTION_SERIALIZATION;
		} else if (Object.class.isAssignableFrom(classType)) {
			return objectSerialization; // 如果不是上面类型，则当做自定义对象序列化
		} else {
			throw new IllegalArgumentException("unknown type[" + classType.getName() + "]");
		}
	}
}
