package connections.connections_api.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "applicant_links")
public class ApplicantLink {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // Corresponds to the auto-incrementing primary key

    @Column(name = "user_id", nullable = false)
    private Integer userId;  // The foreign key to the applicant's user ID

    @Column(name = "token", nullable = false, unique = true)
    private String token;  // The unique token associated with the applicant

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
