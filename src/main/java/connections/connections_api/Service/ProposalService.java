package connections.connections_api.Service;

import connections.connections_api.dto.AllProposalDataDto;
import connections.connections_api.dto.FirstMeetDto;
import connections.connections_api.dto.HomepageDto;
import connections.connections_api.dto.ProposalDto;

public interface ProposalService {
	
	AllProposalDataDto getAllProposalDataByUserId(Integer userId);
	
	ProposalDto saveProposalDataByUserId(Integer userId, ProposalDto proposalDto);

	FirstMeetDto saveFirstMeetDataByUserId(Integer userId, FirstMeetDto firstMeetDto);

	FirstMeetDto getFirstMeetDataByUserId(Integer userId);

	ProposalDto getProposalDataByUserId(Integer userId);
	
	FirstMeetDto getDefaultFirstMeetData();
	
	ProposalDto getDefaultProposalData();

	HomepageDto getHomepageDataByUserId(Integer userId);

	HomepageDto saveHomepageDataByUserId(Integer userId, HomepageDto homepageDto);
}
