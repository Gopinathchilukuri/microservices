package com.redpine.api.controller;

import java.io.IOException;
import org.apache.log4j.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class JwtFilter implements Filter {
	final static Logger logger = Logger.getLogger(JwtFilter.class);
    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {
       
    	
    	System.out.println("we are in JwtFilter.doFilter()");
    	final HttpServletRequest request = (HttpServletRequest) req;
    	final HttpServletResponse response = (HttpServletResponse) res;
    	response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,x-requested-with,Origin, Accept, x-auth-token, Content-Type, Access-Control-Request-Method, Access-Control-Request-Header");
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        	//response.sendError(401, "Invalid Authorization.");
            throw new ServletException("Missing or invalid Authorization header.");
        //	throw new AuthenticationException("Missing or invalid Authorization header.");
        	
       // 	response.setStatus(401);
        	
        }
        final String token = authHeader.substring(7); // The part after "Bearer "

        try {
            final Claims claims = Jwts.parser().setSigningKey("secretkey")
                .parseClaimsJws(token).getBody();
            request.setAttribute("claims", claims);
        }
        catch (final SignatureException e) {
            throw new ServletException("Invalid token.");
        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
          System.out.println("we are in Filter destroy().");
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        System.out.println("we are in Filter init().");
    }

}
