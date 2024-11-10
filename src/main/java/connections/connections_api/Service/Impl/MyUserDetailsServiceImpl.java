package connections.connections_api.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import connections.connections_api.Entity.UserPrincipal;
import connections.connections_api.Entity.Users;
import connections.connections_api.Repository.UserRepositoryInterface;
import connections.connections_api.common.Exceptions.IncorrectPasswordException;
import connections.connections_api.common.Exceptions.UserNotFoundException;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsServiceImpl.class);

	@Autowired
	private UserRepositoryInterface userRepositoryInterface;

	@Autowired
	@Lazy
	 private AuthenticationManager authManager;
	
	@Autowired
	private JwtServiceImpl jwtService;
	
	
	@Override
	public UserDetails loadUserByUsername(String userEmail) {
		Optional<Users> existingUser = userRepositoryInterface.findByUserEmail(userEmail);
	    Users user = existingUser.get();
	    logger.debug("User found in db: "+userEmail);
	    return new UserPrincipal(user);	
	}

	public String registerUser(Users user) {
		Optional<Users> existingUser = userRepositoryInterface.findByUserEmail(user.getUserEmail());
		if(existingUser.isPresent()) {
			return "Applicant already registered. Please login Using login button";
		}
		user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
		userRepositoryInterface.save(user);
    	logger.debug("User registered successfully");
		return "Applicant registered Successfully";
	}

	public String verify(Users user) {
	   userRepositoryInterface.findByUserEmail(user.getUserEmail())
	                .orElseThrow(() -> new UserNotFoundException("User does not exist"));
		 
		try { 
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getPassword()));
		
		if(authentication.isAuthenticated()) {
	    	logger.debug("user authenticated");
			return jwtService.generateToken(user.getUserEmail());
		}
		}
		catch(BadCredentialsException ex){
			throw new IncorrectPasswordException("Password does not match");
		}
		return "Unable to login";
	}
}
