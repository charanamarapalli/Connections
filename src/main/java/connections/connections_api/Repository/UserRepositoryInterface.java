package connections.connections_api.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import connections.connections_api.Entity.Users;

public interface UserRepositoryInterface extends JpaRepository<Users, Integer>{

	Users findByUserName(String userName);
	
	Optional<Users> findByUserEmail(String emailId);
}
