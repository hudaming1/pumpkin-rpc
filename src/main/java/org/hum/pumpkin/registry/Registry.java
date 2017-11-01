package org.hum.pumpkin.registry;

import java.util.List;

public interface Registry {

	public void connect(String address, int port);

	public void registry(String nodeName, String ip, int port);

	public List<String> discover(String nodeName);
}
