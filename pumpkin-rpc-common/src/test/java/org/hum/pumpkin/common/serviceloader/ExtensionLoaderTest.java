package org.hum.pumpkin.common.serviceloader;

import org.hum.pumpkin.api.UserServiceApi;
import org.junit.Test;

public class ExtensionLoaderTest {

	@Test
	public void test() {
		ExtensionLoader<UserServiceApi> extensionLoader = ExtensionLoader.getExtensionLoader(UserServiceApi.class);
		System.out.println(extensionLoader.get("default").sayHello("huming"));
		System.out.println(extensionLoader.get("ext").sayHello("huming"));
		System.out.println(extensionLoader.get("default").sayHello("huming"));
	}
}
