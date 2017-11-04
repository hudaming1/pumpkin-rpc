package org.hum.pumpkin.transport.factory;

import org.hum.pumpkin.transport.Transporter;
import org.hum.pumpkin.transport.config.TransporterConfig;

public interface TransporterFactory {

	public Transporter create(TransporterConfig transporterConfig);
}
