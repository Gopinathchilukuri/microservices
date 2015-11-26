package com.redpine.api.service;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class API_Authentication {
  final static Logger logger = Logger.getLogger(API_Authentication.class);
  public String getToken(String username, String password){
		//String username = "MyWyzBee";
		//String password = "MyWyzBee@123#";
		//String tenant = "MyWyzBee@123%";
		String token = username +":"+password;
		String encodedBytes = Base64.encodeBase64String(token.getBytes());
		String encodedToken = "Basic "+encodedBytes;
		return encodedToken;
	}
}
