package connections.connections_api.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Sections {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "section_id")
	private int sectionId;
	
	@Column(name="section_name", unique=true, nullable=false)
	private String sectionName;
	
}
