package org.hum.pumpkin.test.serialization;

import java.io.IOException;

import org.hum.pumpkin.test.serialization.model.EmployeeModel;

public abstract class AbstractSerializationTest {

	static final int loopCount = 100000;
	private static final EmployeeModel emp = EmployeeModel.createSimple("zhangsan", false, 5000.00D);

	public static Result test(AbstractSerializationTest testSerialization) throws IOException {
		long writeSize = 0;
		long start = System.currentTimeMillis();
		for (int i = 0; i < loopCount; i++) {
			writeSize += testSerialization.serialize(emp).length;
		}
		return new Result(writeSize, (System.currentTimeMillis() - start));
	}

	public abstract byte[] serialize(Object object);

	public static class Result {
		public long size;
		public long cost;

		public Result(long size, long cost) {
			super();
			this.size = size;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "cost " + cost + "ms, write size " + size + " bytes";
		}
	}
}
