package connections.connections_api.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import connections.connections_api.Entity.Homepage;

public interface HomepageRepository extends JpaRepository<Homepage, Integer>{
	
	@Query(value = "select * from first_meet where fk_home_page_user_id = :userId", nativeQuery = true )
	Optional<Homepage> findByUserId(@Param("userId") Integer userId);

}
