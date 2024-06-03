package cgu.im.helloworld01;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import cgu.im.helloworld01.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtService jwtservice;
	
	public AuthenticationFilter(JwtService jwtservice) {
		this.jwtservice = jwtservice;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        String jws = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (jws != null) {
            // Verify token and get user
            String user = jwtservice.getAuthUser(request);
            
            // Authenticate
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null,
                java.util.Collections.emptyList());
            SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
		
	}

}
