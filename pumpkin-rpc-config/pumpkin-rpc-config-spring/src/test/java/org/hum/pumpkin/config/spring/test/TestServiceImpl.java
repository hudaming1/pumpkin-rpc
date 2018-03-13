package org.hum.pumpkin.config.spring.test;

public class TestServiceImpl implements TestService {

	public String sayHello(String name) {
		return "helloo " + name;
	}
}
