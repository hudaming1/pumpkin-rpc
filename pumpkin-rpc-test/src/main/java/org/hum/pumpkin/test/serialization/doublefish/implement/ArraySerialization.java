package org.hum.pumpkin.test.serialization.doublefish.implement;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ArraySerialization {

	public static class IntegerArraySerialization extends AbstractSerialization<Integer[]> {

		@Override
		public Integer[] read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			int len = dataInputStream.readInt();
			Integer[] arr = new Integer[len];
			for (int i = 0; i < len; i++) {
				arr[i] = dataInputStream.readInt();
			}
			return arr;
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);

			Integer[] arr = (Integer[]) obj;
			outputStream.writeInt(arr.length);
			for (int i = 0; i < arr.length; i++) {
				outputStream.writeInt(arr[i]);
			}
		}
	}

	public static class IntArraySerialization extends AbstractSerialization<int[]> {

		@Override
		public int[] read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			int len = dataInputStream.readInt();
			int[] arr = new int[len];
			for (int i = 0; i < len; i++) {
				arr[i] = dataInputStream.readInt();
			}
			return arr;
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);

			int[] arr = (int[]) obj;
			outputStream.writeInt(arr.length);
			for (int i = 0; i < arr.length; i++) {
				outputStream.writeInt(arr[i]);
			}
		}
	}

	public static class ShortArraySerialization extends AbstractSerialization<short[]> {

		@Override
		public short[] read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			int len = dataInputStream.readInt();
			short[] arr = new short[len];
			for (int i = 0; i < len; i++) {
				arr[i] = dataInputStream.readShort();
			}
			return arr;
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);

			short[] arr = (short[]) obj;
			outputStream.writeInt(arr.length);
			for (int i = 0; i < arr.length; i++) {
				outputStream.writeShort(arr[i]);
			}
		}
	}

	public static class ShortWrapArraySerialization extends AbstractSerialization<Short[]> {

		@Override
		public Short[] read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			int len = dataInputStream.readInt();
			Short[] arr = new Short[len];
			for (int i = 0; i < len; i++) {
				arr[i] = dataInputStream.readShort();
			}
			return arr;
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);

			Short[] arr = (Short[]) obj;
			outputStream.writeInt(arr.length);
			for (int i = 0; i < arr.length; i++) {
				outputStream.writeShort(arr[i]);
			}
		}
	}

	public static class LongArraySerialization extends AbstractSerialization<long[]> {

		@Override
		public long[] read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			int len = dataInputStream.readInt();
			long[] arr = new long[len];
			for (int i = 0; i < len; i++) {
				arr[i] = dataInputStream.readLong();
			}
			return arr;
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);

			long[] arr = (long[]) obj;
			outputStream.writeInt(arr.length);
			for (int i = 0; i < arr.length; i++) {
				outputStream.writeLong(arr[i]);
			}
		}
	}

	public static class LongWrapArraySerialization extends AbstractSerialization<Long[]> {

		@Override
		public Long[] read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			int len = dataInputStream.readInt();
			Long[] arr = new Long[len];
			for (int i = 0; i < len; i++) {
				arr[i] = dataInputStream.readLong();
			}
			return arr;
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);

			Long[] arr = (Long[]) obj;
			outputStream.writeInt(arr.length);
			for (int i = 0; i < arr.length; i++) {
				outputStream.writeLong(arr[i]);
			}
		}
	}

	public static class BooleanArraySerialization extends AbstractSerialization<boolean[]> {

		@Override
		public boolean[] read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			int len = dataInputStream.readInt();
			boolean[] arr = new boolean[len];
			for (int i = 0; i < len; i++) {
				arr[i] = dataInputStream.readBoolean();
			}
			return arr;
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);

			boolean[] arr = (boolean[]) obj;
			outputStream.writeInt(arr.length);
			for (int i = 0; i < arr.length; i++) {
				outputStream.writeBoolean(arr[i]);
			}
		}
	}

	public static class BooleanWrapArraySerialization extends AbstractSerialization<Boolean[]> {

		@Override
		public Boolean[] read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			int len = dataInputStream.readInt();
			Boolean[] arr = new Boolean[len];
			for (int i = 0; i < len; i++) {
				arr[i] = dataInputStream.readBoolean();
			}
			return arr;
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);

			Boolean[] arr = (Boolean[]) obj;
			outputStream.writeInt(arr.length);
			for (int i = 0; i < arr.length; i++) {
				outputStream.writeBoolean(arr[i]);
			}
		}
	}

	public static class CharArraySerialization extends AbstractSerialization<char[]> {

		@Override
		public char[] read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			int len = dataInputStream.readInt();
			char[] arr = new char[len];
			for (int i = 0; i < len; i++) {
				arr[i] = dataInputStream.readChar();
			}
			return arr;
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);

			char[] arr = (char[]) obj;
			outputStream.writeInt(arr.length);
			for (int i = 0; i < arr.length; i++) {
				outputStream.writeChar(arr[i]);
			}
		}
	}

	public static class CharacterArraySerialization extends AbstractSerialization<Character[]> {

		@Override
		public Character[] read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			int len = dataInputStream.readInt();
			Character[] arr = new Character[len];
			for (int i = 0; i < len; i++) {
				arr[i] = dataInputStream.readChar();
			}
			return arr;
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);

			Character[] arr = (Character[]) obj;
			outputStream.writeInt(arr.length);
			for (int i = 0; i < arr.length; i++) {
				outputStream.writeChar(arr[i]);
			}
		}
	}


	public static class ByteArraySerialization extends AbstractSerialization<byte[]> {

		@Override
		public byte[] read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			int len = dataInputStream.readInt();
			byte[] arr = new byte[len];
			dataInputStream.read(arr);
			return arr;
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);

			byte[] arr = (byte[]) obj;
			outputStream.writeInt(arr.length);
			outputStream.write(arr);
		}
	}

	public static class ByteWrapArraySerialization extends AbstractSerialization<Byte[]> {

		@Override
		public Byte[] read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			int len = dataInputStream.readInt();
			Byte[] arr = new Byte[len];
			for (int i = 0; i < len; i++) {
				arr[i] = dataInputStream.readByte();
			}
			return arr;
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);

			Byte[] arr = (Byte[]) obj;
			outputStream.writeInt(arr.length);
			for (int i = 0; i < arr.length; i++) {
				outputStream.writeByte(arr[i]);
			}
		}
	}

	public static class FloatArraySerialization extends AbstractSerialization<float[]> {

		@Override
		public float[] read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			int len = dataInputStream.readInt();
			float[] arr = new float[len];
			for (int i = 0; i < len; i++) {
				arr[i] = dataInputStream.readFloat();
			}
			return arr;
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);

			float[] arr = (float[]) obj;
			outputStream.writeInt(arr.length);
			for (int i = 0; i < arr.length; i++) {
				outputStream.writeFloat(arr[i]);
			}
		}
	}

	public static class FloatWrapArraySerialization extends AbstractSerialization<Float[]> {

		@Override
		public Float[] read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			int len = dataInputStream.readInt();
			Float[] arr = new Float[len];
			for (int i = 0; i < len; i++) {
				arr[i] = dataInputStream.readFloat();
			}
			return arr;
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);

			Float[] arr = (Float[]) obj;
			outputStream.writeInt(arr.length);
			for (int i = 0; i < arr.length; i++) {
				outputStream.writeFloat(arr[i]);
			}
		}
	}

	public static class DoubleArraySerialization extends AbstractSerialization<double[]> {

		@Override
		public double[] read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			int len = dataInputStream.readInt();
			double[] arr = new double[len];
			for (int i = 0; i < len; i++) {
				arr[i] = dataInputStream.readDouble();
			}
			return arr;
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);

			double[] arr = (double[]) obj;
			outputStream.writeInt(arr.length);
			for (int i = 0; i < arr.length; i++) {
				outputStream.writeDouble(arr[i]);
			}
		}
	}

	public static class DoubleWrapArraySerialization extends AbstractSerialization<Double[]> {

		@Override
		public Double[] read(DataInputStream dataInputStream, Class<?> classType) throws IOException {
			if (dataInputStream.readByte() == NULL_VALUE) {
				return null;
			}
			int len = dataInputStream.readInt();
			Double[] arr = new Double[len];
			for (int i = 0; i < len; i++) {
				arr[i] = dataInputStream.readDouble();
			}
			return arr;
		}

		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return;
			}
			outputStream.write(NOT_NULL_VALUE);

			Double[] arr = (Double[]) obj;
			outputStream.writeInt(arr.length);
			for (int i = 0; i < arr.length; i++) {
				outputStream.writeDouble(arr[i]);
			}
		}
	}
}
