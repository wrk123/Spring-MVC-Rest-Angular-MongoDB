package com.cyc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.cyc.model.User;
import com.cyc.dao.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Override
	public int saveUserService(User user) {
		logger.info("## Inside save user service method ...");
		return userDAO.saveUser(user);
	}

	@Override
	public List<User> updateUserService(User user) {
		logger.info("## Inside update user service method ::"+user);
		return userDAO.updateUser(user);
	}

	@Override
	public List<User> archieveUserService(String id) {
		logger.info("## Inside archieve user sercvice method ...");
		return userDAO.archieveUser(id);
	}

	@Override
	public User getUserService(String id) {
	logger.info("## Inside get one user  sercvice method ...");
		return userDAO.getUser(id);
	}

	@Override
	public List<User> getAllUserService() {
		logger.info("## Inside get all user service method ...");
		return userDAO.getAllUser();
	}

	
}
