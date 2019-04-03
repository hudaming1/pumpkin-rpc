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

			Integer[] intArr = (Integer[]) obj;
			outputStream.writeInt(intArr.length);
			for (int i = 0; i < intArr.length; i++) {
				outputStream.writeInt(intArr[i]);
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

			int[] intArr = (int[]) obj;
			outputStream.writeInt(intArr.length);
			for (int i = 0; i < intArr.length; i++) {
				outputStream.writeInt(intArr[i]);
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

			short[] shortArr = (short[]) obj;
			outputStream.writeInt(shortArr.length);
			for (int i = 0; i < shortArr.length; i++) {
				outputStream.writeShort(shortArr[i]);
			}
		}
	}
}
