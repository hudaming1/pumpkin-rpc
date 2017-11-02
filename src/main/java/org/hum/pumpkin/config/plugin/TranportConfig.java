package org.hum.pumpkin.config.plugin;

import org.hum.pumpkin.serviceloader.ServiceLoadable;
import org.hum.pumpkin.transport.Transporter;

public class TranportConfig implements ServiceLoadable<Transporter> {

	@Override
	public Class<Transporter> getType() {
		return Transporter.class;
	}
}
