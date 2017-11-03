package org.hum.pumpkin_version1.registry.zookeeper;

import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.hum.pumpkin_version1.registry.Registry;

public class ZookeeperRegistry implements Registry {

	private final String root = "/hm_rpc";
	private ZkClient zkClient;

	public ZookeeperRegistry() {
	}

	@Override
	public void connect(String address, int port) {
		zkClient = new ZkClient(address + ":" + port);
		initDirectory();
	}

	private void initDirectory() {
		if (!zkClient.exists(root)) {
			zkClient.createPersistent(root);
		}
	}

	@Override
	public void registry(String nodeName, String ip, int port) {
		String p = root + "/" + nodeName;
		if (!zkClient.exists(p)) {
			zkClient.createPersistent(p);
		} else if (!zkClient.exists(p + "/" + ip + ":" + port)) {
			zkClient.createEphemeral(p + "/" + ip + ":" + port);
		}
	}

	@Override
	public List<String> discover(String path) {
		String p = root + "/" + path;
		return zkClient.getChildren(p);
	}
}
