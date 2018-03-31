package org.hum.pumpkin.common.serviceloader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import org.junit.Test;

public class ExtenFileUitlTest {

	private static final String FILE_PATH = "META-INF/services";
	
	@Test
	public void testReadFile() throws IOException {
		Enumeration<URL> resources = ExtenFileUitlTest.class.getClassLoader().getResources(FILE_PATH);
		while (resources.hasMoreElements()) {
			System.out.println(resources.nextElement());
		}
		// System.out.println(ExtensionLoader.FileMap.get("org.hum.pumpkin.common.serviceloader.ExtensionLoader"));
	}
}
