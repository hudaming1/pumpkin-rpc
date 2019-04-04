package org.hum.pumpkin.test.serialization.doublefish.implement;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.*;

public class CollectionSerialization<T> extends AbstractSerialization<Collection<T>> {

	@Override
	public Collection<T> read(DataInputStream dataInputStream, Class<Collection<T>> classType) throws IOException {
		if (dataInputStream.readByte() == NULL_VALUE) {
			return null;
		}
		return null;
	}

	@Override
	public void write(DataOutputStream outputStream, Collection<T> obj) throws IOException {
		if (obj == null) {
			outputStream.write(NULL_VALUE);
			return;
		}
		outputStream.write(NOT_NULL_VALUE);
		
	}
}
