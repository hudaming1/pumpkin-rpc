package org.hum.pumpkin.test.serialization.doublefish.implement;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class MapSerialization extends AbstractSerialization<Map> {

	@Override
	public Map read(DataInputStream dataInputStream, Class<Map> classType) throws IOException {
		if (dataInputStream.readByte() == NULL_VALUE) {
			return null;
		}
		
		return null;
	}

	@Override
	public void write(DataOutputStream outputStream, Map obj) throws IOException {
		if (obj == null) {
			outputStream.write(NULL_VALUE);
			return;
		}
		outputStream.write(NOT_NULL_VALUE);
		
	}
}
