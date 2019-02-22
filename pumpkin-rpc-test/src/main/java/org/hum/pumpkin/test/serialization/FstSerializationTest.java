package org.hum.pumpkin.test.serialization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hum.pumpkin.test.serialization.AbstractSerializationTest.Result;

import de.ruedigermoeller.serialization.FSTConfiguration;
import de.ruedigermoeller.serialization.FSTObjectOutput;

public class FstSerializationTest {
	
	static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
	
	public static void main(String[] args) throws IOException {
		Result result = AbstractSerializationTest.test(new AbstractSerializationTest() {
			@Override
			public byte[] serialize(Object object) {
				try {
					final ByteArrayOutputStream bos = new ByteArrayOutputStream();
					FSTObjectOutput out = conf.getObjectOutput(bos);
					out.writeObject(object);
					return bos.toByteArray();
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
		});
		System.out.println(result);
	}
}
