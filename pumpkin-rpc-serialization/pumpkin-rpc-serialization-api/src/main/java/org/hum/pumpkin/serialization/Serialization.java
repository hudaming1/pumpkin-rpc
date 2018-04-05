package org.hum.pumpkin.serialization;

import java.io.InputStream;
import java.io.OutputStream;

import org.hum.pumpkin.common.serviceloader.support.SPI;

@SPI
public interface Serialization {

	void serialize(OutputStream outputStream, Object object);

	<T> T deserialize(InputStream inputStream, Class<T> clazz);
}
