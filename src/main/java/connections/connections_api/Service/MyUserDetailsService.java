package connections.connections_api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import connections.connections_api.Model.UserPrincipal;
import connections.connections_api.Model.Users;
import connections.connections_api.Repository.UserRepositoryInterface;
import connections.connections_api.common.Exceptions.UserNotFoundException;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

	@Autowired
	private UserRepositoryInterface userRepositoryInterface;

	@Autowired
	@Lazy
	 private AuthenticationManager authManager;
	
	@Autowired
	private JwtService jwtService;

	
	@Override
	public UserDetails loadUserByUsername(String userEmail) {
		Optional<Users> existingUser = userRepositoryInterface.findByUserEmail(userEmail);
	    if(existingUser.isEmpty()) {
	    	logger.debug("User not found: "+ userEmail);
	    	throw new UserNotFoundException("User not found: "+ userEmail);
	    }
	    
	    Users user = existingUser.get();
	    logger.debug("User found: "+userEmail);
	    return new UserPrincipal(user);	
	}

	public String registerUser(Users user) {
		user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
		userRepositoryInterface.save(user);
		return "Applicant registered Successfully";
	}

	public String verify(Users user) {
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getPassword()));
		
		if(authentication.isAuthenticated()) {
			return "User logged in";
		}
		return "Unable to login";
	}
}
