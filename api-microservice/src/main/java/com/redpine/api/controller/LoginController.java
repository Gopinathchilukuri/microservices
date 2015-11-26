package com.redpine.api.controller;

import java.io.IOException;
import java.util.Date;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redpine.api.pojo.User;
import com.redpine.api.service.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController {
	final static Logger logger = Logger.getLogger(LoginController.class);
   
    @RequestMapping("/")
    public String index() {
        return "Spring boot running successfully.";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody final UserLogin login, HttpServletResponse response)
        throws ServletException, IOException {
        logger.debug("cursor is in login() " + login);
    	UserRepository ur = new UserRepository();
    	User user=null;
    	System.out.println("we are in /login path.    "+login.name);
		LoginResponse loginResponse = null;
    	try{
    	user= ur.getUser(login.name);
		
    	if (user == null || !user.getUserName().equals(login.name) || !user.getPassword().equals(login.password)) {
        	logger.error("This is error : ");        
            //sending 400 as a response ,invalid credentials
            response.sendError(400, "Invalid credentials");
        }
        else {
        loginResponse = new LoginResponse(Jwts.builder().setSubject(user.getUserName())
            .claim("roles", user.getRole()).setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
        }
       }catch(Exception e) {
       	  logger.error("error occured ",e);
       	 response.sendError(400, "Invalid credentials");
       }
        return  loginResponse;
        
      }
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(){
		logger.debug("cursor is in logout()-debug statemnt ");
		return null;
	}	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signUp(){
		return null;
	}
  @SuppressWarnings("unused")
    private static class UserLogin {
        public String name;
        public String password;
    }

    @SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }
	
}   