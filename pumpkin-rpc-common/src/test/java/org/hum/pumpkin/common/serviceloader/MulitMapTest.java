package org.hum.pumpkin.common.serviceloader;

import org.hum.pumpkin.common.serviceloader.ExtensionLoader.MulitHashMap;
import org.junit.Assert;
import org.junit.Test;

public class MulitMapTest {

	@Test
	public void test1() {
		MulitHashMap<String, String, String> map = new MulitHashMap<>();
		map.put("A", "a", "1");
		map.put("A", "b", "2");
		map.put("A", "c", "3");
		map.put("A", "d", "4");
		map.put("A", "e", "5");
		map.put("A", "f", "6");
		map.put("B", "a", "7");
		
		Assert.assertTrue(map.get("A", "a").equals("1"));
		Assert.assertTrue(map.get("A", "b").equals("2"));
		Assert.assertTrue(map.get("B", "a").equals("7"));
	}
}
