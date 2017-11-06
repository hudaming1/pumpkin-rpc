package org.hum.pumpkin.transport.factory;

import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.transport.Transporter;

public interface TransporterFactory {

	public Transporter create(URL url);
}
