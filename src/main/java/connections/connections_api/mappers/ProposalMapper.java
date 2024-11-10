package connections.connections_api.mappers;

import connections.connections_api.Entity.Proposal;
import connections.connections_api.dto.ProposalDto;

public class ProposalMapper {
	
	public static ProposalDto toDto(Proposal proposal) {
		ProposalDto dto = new ProposalDto();
		dto.setDescription(proposal.getDescription());
		dto.setTitle(proposal.getTitle());
		dto.setEnabled(proposal.isEnabled());
		return dto;
		}

}

