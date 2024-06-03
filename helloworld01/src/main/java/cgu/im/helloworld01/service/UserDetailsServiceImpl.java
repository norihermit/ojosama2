package cgu.im.helloworld01.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cgu.im.helloworld01.domain.AppUser;
import cgu.im.helloworld01.domain.AppUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	  private final AppUserRepository repository;
	  
	  public UserDetailsServiceImpl(AppUserRepository repository) {
		  System.out.println("UserDetailsServiceImpl constructor begins.");
	      this.repository = repository;
	  }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		System.out.println("UserDetailsServiceImpl_loadUserByUsername() begins.");
		
		Optional<AppUser> user = repository.findByUsername(username);
		
        UserBuilder builder = null;
        
        if (user.isPresent()) {
            AppUser currentUser = user.get();
            builder = org.springframework.security.core.userdetails.
                      User.withUsername(username);
            builder.password(currentUser.getPassword());
            builder.roles(currentUser.getRole());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        
        return builder.build();
        
	}
	  
}
