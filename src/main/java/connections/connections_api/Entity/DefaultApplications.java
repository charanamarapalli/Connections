package connections.connections_api.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class DefaultApplications {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "application_id")
	private int applicationId;
	
	@Column(name="application_name", unique=true, nullable=false)
	private String applicationName;
	
	@Column(name="application_description", nullable=false)
	private String applicationDescription;
	
}
