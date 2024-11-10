package connections.connections_api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import connections.connections_api.Entity.Proposal;

public interface ProposalRepository extends JpaRepository<Proposal, Integer>{

	@Query(value = "select* from proposal where fk_proposal_user_id= :userId", nativeQuery=true)
	Proposal findByUserId(@Param("userId") Integer userId);
}
