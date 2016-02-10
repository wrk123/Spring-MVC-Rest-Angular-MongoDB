package com.cyc.dao;

import java.util.List;

import com.cyc.model.User;

public interface UserDAO {

	public int saveUser(User user);
	public List<User> updateUser(User user);
	public List<User> archieveUser(String email);
	public User getUser(String email);
	public List<User> getAllUser();
	
}
