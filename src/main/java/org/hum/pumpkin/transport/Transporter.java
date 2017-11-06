package org.hum.pumpkin.transport;

public interface Transporter {
	
	public Object send(Object invocation);
	
	public void close();
}
