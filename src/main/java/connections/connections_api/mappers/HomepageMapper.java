package connections.connections_api.mappers;

import connections.connections_api.Entity.Homepage;
import connections.connections_api.dto.HomepageDto;

public class HomepageMapper {

	public static HomepageDto toDto(Homepage homepage) {
		HomepageDto dto = new HomepageDto();
		dto.setDescription(homepage.getDescription());
		dto.setTitle(homepage.getTitle());
		dto.setEnabled(homepage.isEnabled());
		return dto;
		}
}
