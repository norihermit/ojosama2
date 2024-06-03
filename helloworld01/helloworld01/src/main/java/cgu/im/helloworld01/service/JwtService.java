package cgu.im.helloworld01.service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import java.security.Key;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;
import io.jsonwebtoken.Jwts;

@Component
public class JwtService {
	
	 static final long EXPIRATIONTIME = 86400000; 
	 static final String PREFIX = "Bearer";
	 
	 static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); 
	 
	 public String getToken(String username) {
		 String token = Jwts.builder()
		 .setSubject(username)
		 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
		 .signWith(key)
		 .compact(); 
		 return token;
	 }	
	 
	 public String getAuthUser(HttpServletRequest request) {

		 System.out.println("getAuthUser begins.");
		 
		    String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		    
		    if (token != null) {
		    	
		        String user = Jwts.parserBuilder()
		        .setSigningKey(key)
		        .build()
		        .parseClaimsJws(token.replace(PREFIX, ""))
		        .getBody()
		        .getSubject();
		        
		        if (user != null) {
		        	System.out.println("getAuthUser ends.");
		          return user;
		        }
		    }
		    
		    System.out.println("getAuthUser ends.");
	        return null;
	 }



}
