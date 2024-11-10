package connections.connections_api.Entity;

import org.hibernate.annotations.ManyToAny;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

public class UserApplications {
	@Id
    @ManyToAny
    @JoinColumn(name = "userId", nullable = false)
    private Users user_id;

    @Id
    @ManyToAny
    @JoinColumn(name = "application_id", nullable = false)
    private DefaultApplications applicationId;
}
