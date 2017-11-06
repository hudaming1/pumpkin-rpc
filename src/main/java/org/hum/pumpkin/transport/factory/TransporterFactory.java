package org.hum.pumpkin.transport.factory;

import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.transport.TransporterServer;
import org.hum.pumpkin.transport.Transporter;

public interface TransporterFactory {

	Transporter createTransporter(URL url);
	
	TransporterServer createServer(URL url);
}
