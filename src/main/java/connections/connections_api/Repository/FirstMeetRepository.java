package connections.connections_api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import connections.connections_api.Entity.FirstMeet;

public interface FirstMeetRepository extends JpaRepository<FirstMeet, Integer>{

	@Query(value = "select * from first_meet where fk_first_meet_user_id = :userId", nativeQuery = true )
	FirstMeet findByUserId(@Param("userId") Integer userId);

}
