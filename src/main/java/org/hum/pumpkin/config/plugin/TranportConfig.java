package org.hum.pumpkin.config.plugin;

import org.hum.pumpkin.serviceloader.ServiceLoadable;
import org.hum.pumpkin.transport.Transporter2;

public class TranportConfig implements ServiceLoadable<Transporter2> {

	@Override
	public Class<Transporter2> getType() {
		return Transporter2.class;
	}
}
