package connections.connections_api.mappers;

import connections.connections_api.Entity.FirstMeet;
import connections.connections_api.dto.FirstMeetDto;

public class FirstMeetMapper {
	
	public static FirstMeetDto toDto(FirstMeet firstMeet) {
		FirstMeetDto dto = new FirstMeetDto();
		dto.setDescription(firstMeet.getDescription());
		dto.setTitle(firstMeet.getTitle());
		dto.setEnabled(firstMeet.isEnabled());
		System.out.println("i cam from "+ dto.getDescription());
		return dto;
		}

}
