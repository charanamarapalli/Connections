package connections.connections_api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FirstMeetDto {

	private String title;
	private String description;
	private boolean isEnabled;	
}