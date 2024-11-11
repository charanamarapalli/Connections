package connections.connections_api.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import connections.connections_api.Entity.FirstMeet;
import connections.connections_api.Entity.Proposal;
import connections.connections_api.Entity.Users;
import connections.connections_api.Repository.FirstMeetRepository;
import connections.connections_api.Repository.ProposalRepository;
import connections.connections_api.Service.MyUserDetailsService;
import connections.connections_api.Service.ProposalService;
import connections.connections_api.dto.AllProposalDataDto;
import connections.connections_api.dto.FirstMeetDto;
import connections.connections_api.dto.ProposalDto;
import connections.connections_api.mappers.FirstMeetMapper;
import connections.connections_api.mappers.ProposalMapper;

@Service
public class ProposalServiceImpl implements ProposalService {

	private static final Logger logger = LoggerFactory.getLogger(ProposalService.class);

	@Autowired
	private FirstMeetRepository firstMeetRepository;

	@Autowired
	private ProposalRepository proposalRepository;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Override
	public AllProposalDataDto getAllProposalDataByUserId(Integer userId) {
		//		AllProposalDataDto allProposalDataDto = new AllProposalDataDto();
		//		FirstMeet firstMeet = firstMeetRepository.findByUserId(userId);
		//		Proposal proposal = proposalRepository.findByUserId(userId);			
		//		allProposalDataDto.setFirstMeetDto(List.of(FirstMeetMapper.toDto(firstMeet)));
		//		allProposalDataDto.setProposalDto(List.of(ProposalMapper.toDto(proposal)));
		//		return allProposalDataDto;
		return null;

	}

	@Override
	public FirstMeetDto saveFirstMeetDataByUserId(Integer userId, FirstMeetDto firstMeetDto) {

		Optional<FirstMeet> existingFirstMeet = firstMeetRepository.findByUserId(userId);
		if(existingFirstMeet.isPresent()) {
			FirstMeet firstMeet = existingFirstMeet.get();
			firstMeet.setTitle(firstMeetDto.getTitle());
			firstMeet.setDescription(firstMeetDto.getDescription());
			firstMeet.setEnabled(firstMeetDto.isEnabled());
		}
		else{
			FirstMeet firstMeet = new FirstMeet();
			Users user= new Users();
			user.setUserId(userId);
			firstMeet.setTitle(firstMeetDto.getTitle());
			firstMeet.setDescription(firstMeetDto.getDescription());
			firstMeet.setUser(user);
			firstMeetRepository.save(firstMeet);
		}


		return getFirstMeetDataByUserId(userId);
	}

	@Override
	public ProposalDto saveProposalDataByUserId(Integer userId, ProposalDto proposalDto) {
		Proposal proposal = new Proposal();
		proposal.setTitle(proposalDto.getTitle());
		proposal.setDescription(proposalDto.getDescription());
		proposalRepository.save(proposal);

		return getProposalDataByUserId(userId);

	}

	@Override
	public FirstMeetDto getFirstMeetDataByUserId(Integer userId) {
		Optional<FirstMeet> firstMeet= firstMeetRepository.findByUserId(userId);
		if(firstMeet.isPresent()) {
			return FirstMeetMapper.toDto(firstMeet.get());
		}
		return null;
	}

	@Override
	public ProposalDto getProposalDataByUserId(Integer userId) {
		Optional<Proposal> proposal = proposalRepository.findByUserId(userId);
		if(proposal.isPresent()) {
			return ProposalMapper.toDto(proposal.get());
		}
		return null;

	}

	@Override
	public FirstMeetDto getDefaultFirstMeetData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProposalDto getDefaultProposalData() {
		// TODO Auto-generated method stub
		return null;
	}



}
