package connections.connections_api.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import connections.connections_api.Entity.FirstMeet;
import connections.connections_api.Entity.Proposal;
import connections.connections_api.Entity.Users;
import connections.connections_api.Repository.FirstMeetRepository;
import connections.connections_api.Repository.ProposalRepository;
import connections.connections_api.Repository.UserRepositoryInterface;
import connections.connections_api.Service.ProposalService;
import connections.connections_api.common.Exceptions.UserNotFoundException;
import connections.connections_api.dto.AllProposalDataDto;
import connections.connections_api.dto.FirstMeetDto;
import connections.connections_api.dto.ProposalDto;
import connections.connections_api.mappers.FirstMeetMapper;
import connections.connections_api.mappers.ProposalMapper;

@Service
public class ProposalServiceImpl implements ProposalService {

	//private static final Logger logger = LoggerFactory.getLogger(ProposalService.class);

	@Autowired
	private UserRepositoryInterface userRepositoryInterface;

	@Autowired
	private FirstMeetRepository firstMeetRepository;

	@Autowired
	private ProposalRepository proposalRepository;
	@Override
	public AllProposalDataDto getAllProposalDataByUserId(int userId) {
		Users users = userRepositoryInterface.findByUserId(userId)
				.orElseThrow(()->new UserNotFoundException("User not found"));	

		AllProposalDataDto allProposalDataDto = new AllProposalDataDto();
		allProposalDataDto.setUserEmail(users.getUserEmail());
		FirstMeet firstMeet = firstMeetRepository.findByUserId(userId);
		Proposal proposal = proposalRepository.findByUserId(userId);			
		allProposalDataDto.setFirstMeetDto(List.of(FirstMeetMapper.toDto(firstMeet)));
		allProposalDataDto.setProposalDto(List.of(ProposalMapper.toDto(proposal)));
		return allProposalDataDto;

	}

	@Override
	public AllProposalDataDto saveFirstMeetDataByUserId(int userId, FirstMeetDto firstMeetDto) {

		userRepositoryInterface.findByUserId(userId)
		.orElseThrow(()->new UserNotFoundException("User not found"));
		FirstMeet firstMeet = new FirstMeet();
		firstMeet.setTitle(firstMeetDto.getTitle());
		firstMeet.setDescription(firstMeetDto.getDescription());
		firstMeetRepository.save(firstMeet);

		return getAllProposalDataByUserId(userId);
	}

	@Override
	public AllProposalDataDto saveProposalDataByUserId(int userId, ProposalDto proposalDto) {
		userRepositoryInterface.findByUserId(userId)
		.orElseThrow(()->new UserNotFoundException("User not found"));
		Proposal proposal = new Proposal();
		proposal.setTitle(proposalDto.getTitle());
		proposal.setDescription(proposalDto.getDescription());
		proposalRepository.save(proposal);

		return getAllProposalDataByUserId(userId);

	}



}
