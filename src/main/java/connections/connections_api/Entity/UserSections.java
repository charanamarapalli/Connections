package connections.connections_api.Entity;

import org.hibernate.annotations.ManyToAny;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

public class UserSections {
	
	@Id
    @ManyToAny
    @JoinColumn(name = "userId", nullable = false)
    private Users user;

    @Id
    @ManyToAny
    @JoinColumn(name = "sectionId", nullable = false)
    private Sections defaultSection;
	
}
