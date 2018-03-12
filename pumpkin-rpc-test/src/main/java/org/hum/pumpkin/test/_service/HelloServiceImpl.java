package org.hum.pumpkin.test._service;

public class HelloServiceImpl implements IHelloService {

	public String sayHello(String name) {
		return "hi " + name;
	}
}
