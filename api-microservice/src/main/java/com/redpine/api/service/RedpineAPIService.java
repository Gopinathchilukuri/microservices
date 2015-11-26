package com.redpine.api.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;
import org.apache.log4j.Logger;

public class RedpineAPIService {
	final static Logger logger = Logger.getLogger(RedpineAPIService.class);

	
	// Get documents with merchantId
	public JSONObject getVersion(String username, String password) {
     API_Authentication authentication = new API_Authentication();
     String token = authentication.getToken(username, password);
		try {
			String webserviceURL = "http://52.74.22.85:8080/wyzbee/api/system/version";
			URL url = new URL(webserviceURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization",
					token);
        //Basic TXlXeXpCZWU6TXlXeXpCZWVAMTIzIw==
			conn.setRequestProperty("X-Wyzbee-Tenant", "MyWyzBee@123%");
			//conn.setRequestProperty("X-BAASBOX-APPCODE", Constants.MBAASAPPCODE);
			//conn.setRequestProperty("X-BB-SESSION", token);

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			// System.out.println("Output from getDocumentsWithPlugin Server .... \n");
			while ((output = br.readLine()) != null) {
				JSONObject jsonObj = new JSONObject(output);
				System.out.println("result from redpine rest............."+jsonObj.toString());
				
				return jsonObj;
				
			}
			
			conn.disconnect();
		} catch (MalformedURLException e) {
			//logger.error(e.getStackTrace());
			e.printStackTrace();

		} catch (IOException e) {
			//logger.error(e.getStackTrace());
			e.printStackTrace();

		}
     catch(Exception e){
       e.printStackTrace();
     }
		return null;
	}

	

}