package org.hum.pumpkin.registry;

import java.util.Objects;

public class EndPoint {

	private String address;
	private int port;
	
	public EndPoint() {
	}
	
	public EndPoint(String address, int port) {
		this.address = address;
		this.port = port;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof EndPoint)) {
			return false;
		} else if (obj == this) {
			return true;
		}
		EndPoint endPoint = (EndPoint) obj;
		return Objects.equals(this.address, endPoint.address) && Objects.equals(this.port, endPoint.port);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.address, this.port);
	}

	@Override
	public String toString() {
		return "EndPoint [address=" + address + ", port=" + port + "]";
	}
}
