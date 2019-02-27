package org.hum.pumpkin.test.serialization.doublefish.implement;

import java.io.DataOutputStream;
import java.io.IOException;

public class BasicTypeOutput {
	
	private static final byte NULL_VALUE = 0;
	private static final byte NOT_NULL_VALUE = 1;

	public static class IntegerOutput extends AbstractOutput {
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

	public static class LongOutput extends AbstractOutput {
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

	public static class StringOutput extends AbstractOutput {
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
	
	public static class DoubleOutput extends AbstractOutput {
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
