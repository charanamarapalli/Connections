package connections.connections_api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AllProposalDataDto {
	private String userEmail;
	private List<FirstMeetDto> firstMeetDto;
	private List<ProposalDto> proposalDto;
}

