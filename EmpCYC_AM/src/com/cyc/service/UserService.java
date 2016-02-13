package com.cyc.service;

import java.util.List;

import com.cyc.model.User;

public interface UserService {

	public int saveUserService(User user);
	public List<User> updateUserService(User user);
	public List<User> archieveUserService(String id);
	public User getUserService(String id);
	public List<User> getAllUserService();
}
