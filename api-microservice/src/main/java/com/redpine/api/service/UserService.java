package com.redpine.api.service;
import org.apache.log4j.Logger;
import org.json.*;
import com.redpine.api.service.*;
import java.net.URL;
import java.io.BufferedReader;
import java.io.*;
import java.net.HttpURLConnection;
import java.io.DataOutputStream;
import javax.net.ssl.HttpsURLConnection;

public class UserService {
	final static Logger logger = Logger.getLogger(UserService.class);
	
	public String createUser(JSONObject user){
		 API_Authentication authentication = new API_Authentication();
		 String username=user.getString("username");
      	 String password = user.getString("password");
    	 String token = authentication.getToken("MyWyzBee", "MyWyzBee@123#");
   		 System.out.println("the Token for authenticatoinn is  "+token);
   		 StringBuilder sb = new StringBuilder(); 
   		 String url="http://52.74.22.85:8080/wyzbee/api/users";
   		 String result="";
			try{
			URL object=new URL(url);
			HttpURLConnection con = (HttpURLConnection) object.openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", token);
			con.setRequestProperty("X-WyzBee-Tenant", "MyWyzBee@123%");   // tenant is hardcoded
			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
			wr.write(user.toString());
			wr.flush();
			//display what returns the POST request
			int httpStatus = con.getResponseCode();
			System.out.println("Redpine result status  "+httpStatus);  
		    if(httpStatus == HttpURLConnection.HTTP_OK){
			
   				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));  
   				String line = null;  
  				while ((line = br.readLine()) != null) {  
        		sb.append(line + "\n");  
    		}  
    		br.close();  
			result=sb.toString();
    		System.out.println("Connection success"+sb.toString());  
		}else{
   		 System.out.println("Connection failed response"+con.getResponseMessage());  
	}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	public String updateUser(JSONObject user){
		 API_Authentication authentication = new API_Authentication();
		 String username=user.getString("username");
      	 String password = user.getString("password");
    	 String token = authentication.getToken("MyWyzBee", "MyWyzBee@123#");
   		 System.out.println("the Token for authenticatoinn is  "+token);
   		 StringBuilder sb = new StringBuilder(); 
   		 String url="http://52.74.22.85:8080/wyzbee/api/users/"+username.trim();
   		 String result="";
			try{
			System.out.println("user Object is "+user);
			System.out.println("user Object.toString is "+user.toString());
			URL object=new URL(url);
			HttpURLConnection con = (HttpURLConnection) object.openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestMethod("PUT");
			con.setRequestProperty("Authorization", token);
			con.setRequestProperty("X-WyzBee-Tenant", "MyWyzBee@123%");   // tenant is hardcoded
			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
			wr.write(user.toString());
			wr.flush();
			//display what returns the POST request
			int httpStatus = con.getResponseCode(); 
			System.out.println("Redpine result status"+httpStatus);
		if(httpStatus == HttpURLConnection.HTTP_OK){
   				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));  
   				String line = null;  
  				while ((line = br.readLine()) != null) {  
        		sb.append(line + "\n");  
    		}  
    		br.close();  
			result=sb.toString();
    		System.out.println("String Builder data is "+sb.toString());  
		}else{
   			 System.out.println("Connection response is "+con.getResponseMessage());  
		}
		}catch(Exception e){
		//System.out.println(e.printStackTrace());
			e.printStackTrace();
		}
		return result;
	}
	
	public String deleteUser(String userName ,Boolean force){
		 API_Authentication authentication = new API_Authentication();
		
    	 String token = authentication.getToken("MyWyzBee", "MyWyzBee@123#");
   		 System.out.println("the Token for authenticatoinn is  "+token);
   		 StringBuilder sb = new StringBuilder();
   		 String url="http://52.74.22.85:8080/wyzbee/api/users/"+userName.trim()+"?force="+force;
   		 String result="";
			try{
			URL object=new URL(url);
			HttpURLConnection con = (HttpURLConnection) object.openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestMethod("DELETE");
			con.setRequestProperty("Authorization", token);
			con.setRequestProperty("X-WyzBee-Tenant", "MyWyzBee@123%");   // tenant is hardcoded
			
			//display what returns the POST request
			int httpStatus = con.getResponseCode();
			System.out.println("Redpine result status  "+httpStatus);  
		    if(httpStatus == HttpURLConnection.HTTP_OK){
			
   				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));  
   				String line = null;  
  				while ((line = br.readLine()) != null) {  
        		sb.append(line + "\n");  
    		}  
    		br.close();  
			result=sb.toString();
    		System.out.println("Connection success"+sb.toString());  
		}else{
   		 System.out.println("Connection failed response"+con.getResponseMessage());  
	}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
