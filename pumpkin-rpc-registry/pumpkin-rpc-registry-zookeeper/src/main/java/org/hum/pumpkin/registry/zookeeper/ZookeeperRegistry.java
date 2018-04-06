package org.hum.pumpkin.registry.zookeeper;

import java.util.ArrayList;
import java.util.List;
import org.I0Itec.zkclient.ZkClient;
import org.hum.pumpkin.registry.EndPoint;
import org.hum.pumpkin.registry.Registry;

public class ZookeeperRegistry implements Registry {

	private final String root = "/hm_rpc";
	private ZkClient zkClient;

	public ZookeeperRegistry() {
	}

	@Override
	public void connect(EndPoint endPoint) {
		zkClient = new ZkClient(endPoint.getAddress() + ":" + endPoint.getPort());
		initDirectory();
	}

	private void initDirectory() {
		if (!zkClient.exists(root)) {
			zkClient.createPersistent(root);
		}
	}

	public void registry(Class<?> clazz, EndPoint endPoint) {
		String p = root + "/" + clazz.getName();
		if (!zkClient.exists(p)) {
			zkClient.createPersistent(p);
		} else if (!zkClient.exists(p + "/" +endPoint.getAddress() + ":" + endPoint.getPort())) {
			zkClient.createEphemeral(p + "/" + endPoint.getAddress() + ":" + endPoint.getPort());
		}
	}
	
	public List<EndPoint> discover(String path) {
		String p = root + "/" + path;
		List<String> urls = zkClient.getChildren(p);
		List<EndPoint> endPointList = new ArrayList<>();
		for (String url : urls) {
			String[] _url = url.split(":");
			endPointList.add(new EndPoint(_url[0], Integer.parseInt(_url[1])));
		}
		return endPointList;
	}
}
