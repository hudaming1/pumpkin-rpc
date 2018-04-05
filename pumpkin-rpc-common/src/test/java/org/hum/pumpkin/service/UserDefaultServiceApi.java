package org.hum.pumpkin.service;

import org.hum.pumpkin.api.UserServiceApi;

public class UserDefaultServiceApi implements UserServiceApi {

	@Override
	public String sayHello(String name) {
		return "hello " + name;
	}
}
