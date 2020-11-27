package com.karson.service.impl;

import com.karson.bean.User;
import com.karson.dao.UserDao;
import com.karson.dao.impl.UserDaoImpl;
import com.karson.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDaoImpl();

	@Override
	public User login(User user) {
		return userDao.getUserByNameAndPassword(user);
	}

	@Override
	public boolean regist(User user) {
		return userDao.registUser(user);
	}

	@Override
	public boolean checkUser(User user) {
		User userByUsername = userDao.getUserByUsername(user);
		return userByUsername == null;
	}
}
