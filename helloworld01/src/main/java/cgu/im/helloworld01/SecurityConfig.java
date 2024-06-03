package cgu.im.helloworld01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cgu.im.helloworld01.service.UserDetailsServiceImpl;
import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationFilter authenticationFilter;
    private final AuthEntryPoint exceptionHandler;
    
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, 
    					  AuthenticationFilter authenticationFilter,
    					  AuthEntryPoint exceptionHandler) {
    	
    	System.out.println("SecurityConfig constructor begins.");
        this.userDetailsService = userDetailsService;
        this.authenticationFilter = authenticationFilter;
        this.exceptionHandler = exceptionHandler;
    }
	
    @Bean
    public PasswordEncoder passwordEncoder() {
    	System.out.println("SecurityConfig_passwordEncoder() begins.");
        return new BCryptPasswordEncoder();
    }
    
    
    // Add a new configureGlobal method to enable users from the database
    public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
    	
    	System.out.println("SecurityConfig_configureGlobal() begins.");
    	auth.userDetailsService(userDetailsService)
    	    .passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Bean
    public AuthenticationManager uthenticationManager
    (AuthenticationConfiguration authConfig) throws Exception {
    	
    	System.out.println("SecurityConfig_uthenticationManager() begins.");
    	return authConfig.getAuthenticationManager();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	System.out.println("SecurityConfig_filterChain() begins.");
    	
        // Add this one p. 286 of textbook
    	
        http.csrf((csrf) -> csrf.disable()).cors(withDefaults())
            .authorizeHttpRequests((authorizeHttpRequests) -> 
                 authorizeHttpRequests.anyRequest().permitAll());
        
    	// commented out for p. 286 of textbook
    	 
//    	http.csrf((csrf) -> csrf.disable())
//    		.cors(withDefaults())
//        	.sessionManagement((sessionManagement) -> 
//    	    	sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//        	.authorizeHttpRequests((authorizeHttpRequests) -> 
//    	    	authorizeHttpRequests.requestMatchers(HttpMethod.POST, "/login")
//        	.permitAll()
//        	.anyRequest()
//        	.authenticated())
//        	.addFilterBefore(authenticationFilter, 
//        			         UsernamePasswordAuthenticationFilter.class)
//        	.exceptionHandling((exceptionHandling) -> exceptionHandling.authenticationEntryPoint(exceptionHandler));
        			         
        

    	return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
    	
        UrlBasedCorsConfigurationSource source =  
          new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(false);
        config.applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", config);
        
        return source;
    }
    
//    @Bean // for in-memory password manager
//    public InMemoryUserDetailsManager userDetailsService() {
//            UserDetails user = User.builder()
//            		.username("user")
//            		.password(passwordEncoder()
//            				  .encode("passwordxxx")) 
//            		.roles("USER")
//            		.build();
//
//            return new InMemoryUserDetailsManager(user);
//    }
    
    


}
