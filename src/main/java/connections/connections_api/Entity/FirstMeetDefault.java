package connections.connections_api.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="first_meet_default")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FirstMeetDefault {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="title", nullable=false, length=100)
	private String title;
	
	@Column(name="description", nullable=false, length=2000)
	private String description;
	
	@Column(name="is_enabled", nullable=false)
	private boolean isEnabled;
}
