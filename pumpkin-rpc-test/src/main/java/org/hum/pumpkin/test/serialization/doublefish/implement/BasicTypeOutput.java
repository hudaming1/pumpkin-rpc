package org.hum.pumpkin.test.serialization.doublefish.implement;

import java.io.DataOutputStream;
import java.io.IOException;

public class BasicTypeOutput {

	public static class IntegerOutput extends AbstractOutput {
		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			outputStream.writeInt((int) obj);
		}
	}

	public static class LongOutput extends AbstractOutput {
		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			outputStream.writeLong((long) obj);
		}
	}

	public static class StringOutput extends AbstractOutput {
		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			outputStream.writeUTF((String) obj);
		}
	}
	
	public static class DoubleOutput extends AbstractOutput {
		@Override
		public void write(DataOutputStream outputStream, Object obj) throws IOException {
			outputStream.writeDouble((double) obj);
		}
	}
}
