package org.hum.pumpkin.serialize;

public interface Serialization {

	byte[] serialize(Object object);
	
	Object deserialize(byte[] bytes);
}
