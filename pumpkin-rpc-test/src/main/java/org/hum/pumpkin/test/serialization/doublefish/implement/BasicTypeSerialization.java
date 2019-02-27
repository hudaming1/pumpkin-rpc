package org.hum.pumpkin.test.serialization.doublefish.implement;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BasicTypeSerialization {
	
	private static final byte NULL_VALUE = 0;
	private static final byte NOT_NULL_VALUE = 1;

	public static class IntegerOutput extends AbstractSerialization<Integer> {

		@Override
		public Integer read(DataInputStream dataInputStream, Class<Integer> classType) throws IOException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return ;
			}
			outputStream.write(NOT_NULL_VALUE);
			outputStream.writeInt((int) obj);
		}
	}

	public static class LongOutput extends AbstractSerialization<Long> {

		@Override
		public Long read(DataInputStream dataInputStream, Class<Long> classType) throws IOException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return ;
			}
			outputStream.write(NOT_NULL_VALUE);
			outputStream.writeLong((long) obj);
		}
	}

	public static class StringOutput extends AbstractSerialization<String> {

		@Override
		public String read(DataInputStream dataInputStream, Class<String> classType) throws IOException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return ;
			}
			outputStream.write(NOT_NULL_VALUE);
			outputStream.writeUTF((String) obj);
		}
	}
	
	public static class DoubleOutput extends AbstractSerialization<Double> {

		@Override
		public Double read(DataInputStream dataInputStream, Class<Double> classType) throws IOException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			if (obj == null) {
				outputStream.write(NULL_VALUE);
				return ;
			}
			outputStream.write(NOT_NULL_VALUE);
			outputStream.writeDouble((double) obj);
		}
	}
}
