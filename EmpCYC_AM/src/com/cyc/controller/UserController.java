package com.cyc.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cyc.service.UserService;
import com.cyc.model.User;
//import com.cyc.model.UserAddress;

@RestController
public class UserController {
	
	
	@Autowired
	UserService userService;
	
	private static Logger logger = Logger.getLogger(UserController.class);
	
	 //Method for getting all user details 
	 @RequestMapping(value="/users",method = RequestMethod.GET,headers="Accept=application/json")
	 public @ResponseBody List<User> getAllUsers() {	 
	  List<User> users=userService.getAllUserService();
	  return users;
	 }
	 
	 
	 @RequestMapping(value="/users/archive/{id}",method = RequestMethod.POST,headers="Accept=application/json")
	 public @ResponseBody List<User> archiveUser(@PathVariable String id) {	
		 userService.archieveUserService(id);
	   List<User> users=userService.getAllUserService();
	  return users;
	
	 }
	 
	 
	 @RequestMapping(value="/users/update/{id}/{firstName}/{lastName}/{email}/{mobile}/{creation_time}/{status}",method = RequestMethod.POST,headers="Accept=application/json")
	 public @ResponseBody List<User> updateUser(@PathVariable String id,@PathVariable String firstName,@PathVariable String lastName,@PathVariable String email,@PathVariable String mobile,@PathVariable long creation_time,@PathVariable String status) throws ParseException {	
		 Date date = new Date(creation_time); 
		 User user = new User();
		   user.setId(id);
		   user.setFirstName(firstName);
		   user.setLastName(lastName);
		   user.setEmail(email);
		   user.setMobile(mobile);
		   user.setLast_update_time(new Date());
		   user.setCreation_time(date);
		   user.setStatus(status);
		   logger.info(":: Setting values in controller ::"+user); 
		   userService.updateUserService(user);
		
		 return userService.getAllUserService();
		 
	 }
	 
	 // Method for saving user details 
	 @RequestMapping(value="/users/insert/{firstName}/{lastName}/{email}/{mobile}/{status}",method = RequestMethod.POST)
	 public List<User> addUser(@PathVariable String firstName,@PathVariable String lastName,@PathVariable String email,@PathVariable String mobile,@PathVariable String status) throws ParseException {	
		logger.info("Inside addUser method ...");
		User user = new User();
		Date date = new Date();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setCreation_time(date);
			user.setLast_update_time(date);
			user.setMobile(mobile);
			user.setStatus(status);
		logger.info("::Setting user values :::["+user+"]");
		userService.saveUserService(user);
		logger.info("Returning the values after adding user ");
		return userService.getAllUserService();
		 
	 }	 	 	 		 
	
	 //Add user including address and other fields 
	/* @RequestMapping(value="/users/insert/{firstName}/{lastName}/{email}/{mobile}/{status}/{city}/{state}",method = RequestMethod.POST,headers="Accept=application/json")
	 public List<User> addUser(@PathVariable String firstName,@PathVariable String lastName,@PathVariable String email,@PathVariable String mobile,@PathVariable String status, @PathVariable String city, @PathVariable String state) throws ParseException {	
		User user = new User();
		UserAddress uAddr=new UserAddress();
		Date date = new Date();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setCreation_time(date);
			user.setLast_update_time(date);
			user.setMobile(mobile);
			user.setStatus(status);
			uAddr.setCity(city);
			uAddr.setState(state);
			uAddr.setCreation_time(new Date());
			uAddr.setLast_modified_time(new Date());
			uAddr.setUser(user);
		Set<UserAddress> userAddr=new HashSet<UserAddress>();
			userAddr.add(uAddr);
			user.setAddr(userAddr);
			
			userService.saveUserService(user);
		
		return userService.getAllUserService();
	 }*/
}