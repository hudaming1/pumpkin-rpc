package org.hum.pumpkin_version1.transport;

public class ServiceKey {

	private Class<?> clazz;
	
	public ServiceKey(Class<?> clazz) {
		this.clazz = clazz;
	}
	
	public String getKey() {
		return clazz.getName();
	}
	
	@Override
	public int hashCode() {
		return getKey().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ServiceKey)) {
			return false;
		}
		ServiceKey serviceKey = (ServiceKey) obj;
		return serviceKey.getKey().equals(getKey());
	}
	
}
