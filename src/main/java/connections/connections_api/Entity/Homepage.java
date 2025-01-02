package connections.connections_api.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="home_page")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Homepage {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
    @JoinColumn(name = "fk_home_page_user_id", referencedColumnName = "userId", nullable = false, unique = true)
	private Users user;

	@Column(name="title", nullable=false, length=100)
	private String title;
	
	@Column(name="description", nullable=false, length=2000)
	private String description;
	
	@Column(name="is_enabled", nullable=false)
	private boolean isEnabled;
}
