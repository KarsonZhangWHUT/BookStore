package com.karson.service;

import com.karson.bean.User;

public interface UserService {
	User login(User user);
	boolean regist(User user);
}
