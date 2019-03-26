package org.hum.pumpkin.test.serialization.doublefish.implement;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 8大基本数据类型
 * 
 * @author huming
 */
public class BasicTypeSerialization {

	private static final byte NULL_VALUE = 0;
	private static final byte NOT_NULL_VALUE = 1;

	public static class BooleanSerialization extends AbstractSerialization<Boolean> {

		@Override
		public Boolean read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			// TODO Auto-generated method stub

		}

	}

	public static class CharSerialization extends AbstractSerialization<Character> {

		@Override
		public Character read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			// TODO Auto-generated method stub

		}

	}

	public static class ShortSerialization extends AbstractSerialization<Short> {

		@Override
		public Short read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			// TODO Auto-generated method stub

		}

	}

	public static class IntegerSerialization extends AbstractSerialization<Integer> {

		@Override
		public Integer read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			return dataInputStream.readInt();
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);
			outputStream.writeInt((int) obj);
		}
	}

	public static class LongSerialization extends AbstractSerialization<Long> {

		@Override
		public Long read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			return dataInputStream.readLong();
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);
			outputStream.writeLong((long) obj);
		}
	}

	public static class FloatSerialization extends AbstractSerialization<Float> {

		@Override
		public Float read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			// TODO Auto-generated method stub

		}

	}

	public static class DoubleSerialization extends AbstractSerialization<Double> {

		@Override
		public Double read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			return dataInputStream.readDouble();
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);
			outputStream.writeDouble((double) obj);
		}
	}

	public static class StringSerialization extends AbstractSerialization<String> {

		@Override
		public String read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			return dataInputStream.readUTF();
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);
			outputStream.writeUTF((String) obj);
		}
	}
}
