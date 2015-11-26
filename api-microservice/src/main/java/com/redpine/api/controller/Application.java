package com.redpine.api.controller;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.apache.log4j.Logger;
import com.redpine.api.controller.JwtFilter;
import com.redpine.api.pojo.User;
import com.redpine.api.service.UserRepository;

@SpringBootApplication
public class Application {
    	final static Logger logger = Logger.getLogger(LoginController.class);
	 @Bean
	    public FilterRegistrationBean jwtFilter() {
		 System.out.println("jwtFilter  ");
			 logger.info("jwtFilter");
	        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	        registrationBean.setFilter(new JwtFilter());
	        registrationBean.addUrlPatterns("/user/*");	
	        

	        return registrationBean;
	    }
	 
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        
        System.out.println("Let's inspect the beans provided by Spring Boot:");
        UserDatabase();
        }
    
    public static void UserDatabase(){
    	UserRepository repo=new UserRepository();
    	// new User("firstName","lastName","username","password","role");
    	User user=new User("winbold","manikya","winbold","Winbold123","Admin");
    	User user1=new User("Gopinath","chilukuri","gopinath.ch","gopi9032","user");
    	User user2=new User("chandu","chandu","chandu","chandu12345","User");
    	repo.addUser(user);
    	repo.addUser(user1);
    	repo.addUser(user2);
    	System.out.println("dummy users are created.");
    }

}
