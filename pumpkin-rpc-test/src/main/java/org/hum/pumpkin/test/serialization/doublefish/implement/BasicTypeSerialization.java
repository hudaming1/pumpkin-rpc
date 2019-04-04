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

	public static class BooleanSerialization extends AbstractSerialization<Boolean> {

		@Override
		public Boolean read(DataInputStream dataInputStream, Class<Boolean> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			return dataInputStream.readBoolean();
		}

		@Override
		public void write(DataOutputStream outputStream, Boolean obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);
			outputStream.writeBoolean((boolean) obj);
		}

	}

	public static class CharSerialization extends AbstractSerialization<Character> {

		@Override
		public Character read(DataInputStream dataInputStream, Class<Character> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			return dataInputStream.readChar();
		}

		@Override
		public void write(DataOutputStream outputStream, Character obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);
			outputStream.writeChar((char) obj);
		}

	}

	public static class ShortSerialization extends AbstractSerialization<Short> {

		@Override
		public Short read(DataInputStream dataInputStream, Class<Short> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			return dataInputStream.readShort();
		}

		@Override
		public void write(DataOutputStream outputStream, Short obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);
			outputStream.writeShort((short) obj);
		}

	}

	public static class IntegerSerialization extends AbstractSerialization<Integer> {

		@Override
		public Integer read(DataInputStream dataInputStream, Class<Integer> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			return dataInputStream.readInt();
		}

		@Override
		public void write(DataOutputStream outputStream, Integer obj) throws IOException {
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
		public Long read(DataInputStream dataInputStream, Class<Long> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			return dataInputStream.readLong();
		}

		@Override
		public void write(DataOutputStream outputStream, Long obj) throws IOException {
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
		public Float read(DataInputStream dataInputStream, Class<Float> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			return dataInputStream.readFloat();
		}

		@Override
		public void write(DataOutputStream outputStream, Float obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);
			outputStream.writeFloat((float) obj);
		}

	}

	public static class DoubleSerialization extends AbstractSerialization<Double> {

		@Override
		public Double read(DataInputStream dataInputStream, Class<Double> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			return dataInputStream.readDouble();
		}

		@Override
		public void write(DataOutputStream outputStream, Double obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);
			outputStream.writeDouble((double) obj);
		}
	}

	public static class ByteSerialization extends AbstractSerialization<Byte> {

		@Override
		public Byte read(DataInputStream dataInputStream, Class<Byte> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			return dataInputStream.readByte();
		}

		@Override
		public void write(DataOutputStream outputStream, Byte obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);
			outputStream.writeByte((byte) obj);
		}
	}

	public static class StringSerialization extends AbstractSerialization<String> {

		@Override
		public String read(DataInputStream dataInputStream, Class<String> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			return dataInputStream.readUTF();
		}

		@Override
		public void write(DataOutputStream outputStream, String obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);
			outputStream.writeUTF((String) obj);
		}
	}
}
