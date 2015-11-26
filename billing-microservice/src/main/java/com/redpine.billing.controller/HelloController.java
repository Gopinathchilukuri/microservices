package com.redpine.billing.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
   
    @RequestMapping("/")
    public String index() {
        return "Application is running successfully.";
    }
    @RequestMapping(value = "/hello")
    public String hello( HttpServletResponse response){
       
        return  "Welcome to Billing microservice.";
    }	
	
}   