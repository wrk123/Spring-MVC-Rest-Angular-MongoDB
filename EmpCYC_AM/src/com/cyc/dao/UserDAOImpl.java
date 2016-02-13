package com.cyc.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
//import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.cyc.model.User;

@Component
public class UserDAOImpl implements UserDAO {

	private static Logger logger = Logger.getLogger(UserDAOImpl.class);
	public static final String COLLECTION_NAME = "user";
	
	@Autowired
	private MongoTemplate mongoTemplate;

	/*@Autowired 
	private MongoOperations mongoOps;*/
	  
	@Override
	public int saveUser(User user) {
		logger.info("#3 collection exists  >> ["+mongoTemplate.collectionExists(User.class)+"]");
		try{
			if(!mongoTemplate.collectionExists(User.class)){
				logger.info("## Creating the class User in mongoOperations ##");
				//mongoOps.createCollection(COLLECTION_NAME);
				mongoTemplate.createCollection(COLLECTION_NAME);   }
		}catch(Exception ex){
			logger.info("## Exception occured while creating collection ## ["+ex+"]");
			ex.printStackTrace();
		}
		
		user.setId(UUID.randomUUID().toString());
		user.setCreation_time(new Date());
		user.setLast_update_time(new Date());
		try{	
			mongoTemplate.insert(user);
			logger.info(" Inserting values of the new user in the collection ["+user+"]");
				//mongoOps.insert(user); 
			}
			catch(Exception e){
				logger.info("## Could not save user Details ##["+e+"]");
				e.printStackTrace();
			}
		
		return 0;
	}

	@Override
	public List<User> updateUser(User user) {
		//user.setLast_update_time(new Date());
		try{
			logger.info("##Updating user information ##["+user.getId()+user.getFirstName()+user.getLastName()+user.getMobile()+user.getStatus()+user.getCreation_time()+user.getLast_update_time()+"]");
		
			Query query = new Query();
			logger.info(":: User Id getting ::"+user.getId());
			query.addCriteria(Criteria.where("id").is(user.getId()));
			logger.info("::: Query object is ::["+query.toString()+"]");
			User userTest1=null;
			try{userTest1 = mongoTemplate.findOne(query, User.class);}catch(Exception e){ logger.info(":: Exception caught in usertest object::["+userTest1+"]"+e); }
			
			logger.info(":: Values fetched from query is ::::["+userTest1.getEmail()+"]");
			userTest1.setFirstName(user.getFirstName());
				userTest1.setLastName(user.getLastName());
				userTest1.setMobile(user.getMobile());
				userTest1.setStatus(user.getStatus());
				userTest1.setCreation_time(user.getCreation_time());
				userTest1.setLast_update_time(new Date());
				userTest1.setEmail(user.getEmail());
			logger.info(":: Before saving user detaiails ::["+userTest1+"]");
			mongoTemplate.save(userTest1);
			
			logger.info("##Updation done  ##");
		}catch(Exception  ex){
			logger.info("## Exception occured, updation failed ##["+ex+"]");
			ex.printStackTrace();
		}
        return getAllUser();
	}

	@Override
	public List<User> archieveUser(String id) {
		User user=new User();
		try{
				Query query= new Query();
				query.addCriteria(Criteria.where("id").is(id));
			//	user = mongoOps.findOne(query, User.class);
				user = (User) mongoTemplate.findOne(query, User.class, COLLECTION_NAME);
				logger.info("## Archiving user, value fetched from the query ##["+user+"]");
		}catch(Exception ex){
				logger.info("## Exception occured while fetching details ##["+ex+"]");
				ex.printStackTrace();
			}
		if(user.getStatus().equalsIgnoreCase("Active"))
	    	{  	user.setStatus("Inactive");
	    		user.setLast_update_time(new Date());
	    	}
	    else{   user.setStatus("Active"); 
	    		user.setLast_update_time(new Date());
	    	}
		try{
			mongoTemplate.save(user);
			//mongoOps.save(user);
			logger.info("Archieveing USER done......");
		}catch(Exception e){
			logger.info("## Unable toupdate user status ##["+e+"]");
			e.printStackTrace();
		}
		
		return getAllUser();
	}

	@Override
	public User getUser(String id) {
		User user=new User();
		try{
			Query query= new Query();
			query.addCriteria(Criteria.where("id").is(id));
			//user = mongoOps.findOne(query, User.class);
			user = mongoTemplate.findOne(query, User.class);
		}catch(Exception e){
			logger.info("## Exception occured, could not get user ##["+e+"]");
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> getAllUser() {
		List<User> user=null;
		try{	
			Query query=new Query();
			//user=mongoOps.find(query,User.class);
			user=mongoTemplate.find(query,User.class);
			//logger.info("Fetching values for all users :::: ["+user+"] ");
		}catch(Exception e){
			logger.info("## Exception occured while executiong the query ###");
			e.printStackTrace();
		}
		return user;
	}


}
