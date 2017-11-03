package org.hum.pumpkin.transport;

public interface TransporterHolder {

	public void join(String address, int port);
	
	public void remove(String address, int port);
	
	public Response send(Request request) ;
}
