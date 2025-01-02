package connections.connections_api.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import connections.connections_api.Entity.ApplicantLink;

public interface ApplicantLinkRepository extends JpaRepository<ApplicantLink, Integer>{
    Optional<ApplicantLink> findByToken(String token); // Query to find ApplicantLink by token
    Optional<ApplicantLink> findByUserId(Integer userId);
}
