package org.hum.pumpkin.test._service.impl;

import org.hum.pumpkin.test._service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

	@Override
	public String getNameById(Integer id) {
		System.out.println("userService receive request");
		return "user" + id;
	}
}
