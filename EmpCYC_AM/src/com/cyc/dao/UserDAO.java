package com.cyc.dao;

import java.util.List;

import com.cyc.model.User;

public interface UserDAO {

	public int saveUser(User user);
	public List<User> updateUser(User user);
	public List<User> archieveUser(String id);
	public User getUser(String id);
	public List<User> getAllUser();
	
}
