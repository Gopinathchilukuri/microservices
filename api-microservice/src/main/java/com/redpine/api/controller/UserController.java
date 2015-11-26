package com.redpine.api.controller;

import java.util.Date;
import org.apache.log4j.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redpine.api.pojo.User;
import com.redpine.api.service.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	final static Logger logger = Logger.getLogger(UserController.class);
        
    @RequestMapping(value = "/createNewUser", method = RequestMethod.POST)
    public  @ResponseBody String createNewUser(@RequestBody String obj) {
      //call create user rest api
      System.out.println("the requested object is "+obj);
      JSONObject jsonObj = new JSONObject(obj);
      System.out.println("json Object is "+jsonObj +"+\n"+jsonObj.length() );
      String username=jsonObj.getString("username");
      String password = jsonObj.getString("password");
	 String result=new UserService().createUser(jsonObj);
	 
	 return result;
    }
	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public  @ResponseBody String updateUser(@RequestBody String obj) {
      //call create user rest api
      System.out.println("the requested object is "+obj);
      JSONObject jsonObj = new JSONObject(obj);
      System.out.println("json Object is "+jsonObj +"+\n"+jsonObj.length() );
     
	 String result=new UserService().updateUser(jsonObj);
	 
	 return result;
    }
  	
  	@RequestMapping(value = "/{userName}/delete", method = RequestMethod.DELETE)
    public  @ResponseBody String updateUser( @PathVariable String userName ,HttpServletRequest request) {
      //call create user rest api
      String param=request.getParameter("force");
      Boolean force=false;
      force=param.equals("true");
      System.out.println("the requested object is "+userName+" force is "+force);
     
	 String result=new UserService().deleteUser(userName,force);
	 
	 return result;
    }
}   