package org.hum.pumpkin_version1.config.plugin;

import org.hum.pumpkin_version1.serviceloader.ServiceLoadable;
import org.hum.pumpkin_version1.transport.Transporter;

public class TranportConfig implements ServiceLoadable<Transporter> {

	@Override
	public Class<Transporter> getType() {
		return Transporter.class;
	}
}
