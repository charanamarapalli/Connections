package connections.connections_api.Service;

import connections.connections_api.dto.AllProposalDataDto;
import connections.connections_api.dto.FirstMeetDto;
import connections.connections_api.dto.ProposalDto;

public interface ProposalService {

	AllProposalDataDto getAllProposalDataByUserId(int userId);
	
	AllProposalDataDto saveFirstMeetDataByUserId(int userId, FirstMeetDto firstMeetDto);
	
	AllProposalDataDto saveProposalDataByUserId(int userId, ProposalDto proposalDto);
}
