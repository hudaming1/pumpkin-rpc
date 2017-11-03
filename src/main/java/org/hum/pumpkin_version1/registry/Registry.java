package org.hum.pumpkin_version1.registry;

import java.util.List;

public interface Registry {

	public void connect(String address, int port);

	public void registry(String nodeName, String ip, int port);

	public List<String> discover(String nodeName);
}
