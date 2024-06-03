package cgu.im.helloworld01.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cgu.im.helloworld01.domain.AccountCredentials;
import cgu.im.helloworld01.service.JwtService;

@RestController
public class LoginController {
	
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	public LoginController(JwtService jwtService, AuthenticationManager authenticationManager) {
		
		System.out.println("LoginController constructore begins.");
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials) {
	// Generate token and send it in the response Authorization header
		
		System.out.println("getToken begins.");
		System.out.println("username:"+credentials.username());
		System.out.println("password:"+credentials.password());
		
		UsernamePasswordAuthenticationToken creds = 
				new  UsernamePasswordAuthenticationToken(
						credentials.username(), credentials.password());		
		
		Authentication auth = authenticationManager.authenticate(creds);
		
		// Generate token
		String jwts = jwtService.getToken(auth.getName());
		
		System.out.println("auth.getName():"+auth.getName());
		System.out.println("jwts:"+jwts);
		System.out.println("getToken ends.");
		
		
		// Build response with the generated token
		return ResponseEntity.ok()
				        .header(HttpHeaders.AUTHORIZATION, "Bearer" + jwts)
				        .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,"Authorization")
				        .build();
		
	}
}
