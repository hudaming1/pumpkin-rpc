package org.hum.pumpkin.test._service.impl;

import org.hum.pumpkin.test._service.IHelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements IHelloService {

	public String sayHello(String name) {
		System.out.println("helloService received request");
		return "hi " + name;
	}
}
