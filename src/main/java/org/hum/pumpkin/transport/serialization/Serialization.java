package org.hum.pumpkin.transport.serialization;

import java.io.InputStream;

/**
 * TODO deserialize该接怎么参数好？需要考虑
 */
public interface Serialization {

	byte[] serialize(Object object);

	<T> T deserialize(byte[] bytes);
	
	<T> T deserialize(InputStream inputStream);
}
