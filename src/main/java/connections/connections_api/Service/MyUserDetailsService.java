package connections.connections_api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import connections.connections_api.Model.UserPrincipal;
import connections.connections_api.Model.Users;
import connections.connections_api.Repository.UserRepositoryInterface;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

	@Autowired
	private UserRepositoryInterface userRepositoryInterface;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepositoryInterface.findByUserName(username);
	    if(user==null) {
	    	logger.debug("User not found from debug");
	    	throw new UsernameNotFoundException("User not found");
	    }
	    
	    return new UserPrincipal(user);
		
	}

	
}
