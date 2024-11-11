package connections.connections_api.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

import connections.connections_api.Entity.Users;

public interface MyUserDetailsService extends UserDetailsService{

	Users verifyUserById(Integer userId);
}
