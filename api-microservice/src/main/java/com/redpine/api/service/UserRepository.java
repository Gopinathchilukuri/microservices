package com.redpine.api.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.redpine.api.pojo.User;
import org.apache.log4j.Logger;


@Configuration
@ComponentScan
public class UserRepository{
final static Logger logger = Logger.getLogger(UserRepository.class);
	private static Map<String, User> userList = new HashMap<String, User>();
	
	public void addUser(User user){
		System.out.println("username is "+user.getUserName());
		userList.put(user.getUserName(), user);
	}
	public User getUser(String username){
		User user=userList.get(username);
		return user;
		
	}
	
	
}