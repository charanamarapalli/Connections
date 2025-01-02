package connections.connections_api.Service.Impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import connections.connections_api.Entity.FirstMeet;
import connections.connections_api.Entity.FirstMeetDefault;
import connections.connections_api.Entity.Homepage;
import connections.connections_api.Entity.Proposal;
import connections.connections_api.Entity.Users;
import connections.connections_api.Repository.FirstMeetDefaultRepository;
import connections.connections_api.Repository.FirstMeetRepository;
import connections.connections_api.Repository.HomepageRepository;
import connections.connections_api.Repository.ProposalRepository;
import connections.connections_api.Service.ProposalService;
import connections.connections_api.dto.AllProposalDataDto;
import connections.connections_api.dto.FirstMeetDto;
import connections.connections_api.dto.HomepageDto;
import connections.connections_api.dto.ProposalDto;
import connections.connections_api.mappers.FirstMeetMapper;
import connections.connections_api.mappers.HomepageMapper;
import connections.connections_api.mappers.ProposalMapper;

@Service
public class ProposalServiceImpl implements ProposalService {

	private static final Logger logger = LoggerFactory.getLogger(ProposalService.class);

	@Autowired
	private FirstMeetRepository firstMeetRepository;

	@Autowired
	private ProposalRepository proposalRepository;
	
	@Autowired
	private HomepageRepository homepageRepository;
	
	@Autowired
	private FirstMeetDefaultRepository firstMeetDefaultRepository;

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
		logger.debug("saving first meet data");

		Optional<FirstMeet> existingFirstMeet = firstMeetRepository.findByUserId(userId);
		if(existingFirstMeet.isPresent()) {
			FirstMeet firstMeet = existingFirstMeet.get();
			firstMeet.setTitle(firstMeetDto.getTitle());
			firstMeet.setDescription(firstMeetDto.getDescription());
			firstMeet.setEnabled(firstMeetDto.isEnabled());
			firstMeetRepository.save(firstMeet);
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
		logger.debug("saving proposal data");

		Optional<Proposal> existingProposal = proposalRepository.findByUserId(userId);
		if(existingProposal.isPresent()) {
			Proposal proposal = existingProposal.get();
			proposal.setTitle(proposalDto.getTitle());
			proposal.setDescription(proposalDto.getDescription());
			proposalRepository.save(proposal);	
		}
		else {
		Proposal proposal = new Proposal();
		proposal.setTitle(proposalDto.getTitle());
		proposal.setDescription(proposalDto.getDescription());
		Users user=new Users();
		user.setUserId(userId);
		proposal.setUser(user);
		proposalRepository.save(proposal);
		}

		return getProposalDataByUserId(userId);
	}
	
	@Override
	public HomepageDto saveHomepageDataByUserId(Integer userId, HomepageDto homepageDto) {
		logger.debug("saving proposal data");

		Optional<Homepage> existingHomepage = homepageRepository.findByUserId(userId);
		if(existingHomepage.isPresent()) {
			Homepage homepage = existingHomepage.get();
			homepage.setTitle(homepageDto.getTitle());
			homepage.setDescription(homepageDto.getDescription());
			homepageRepository.save(homepage);	
		}
		else {
			Homepage homepage = new Homepage();
			homepage.setTitle(homepageDto.getTitle());
			homepage.setDescription(homepageDto.getDescription());
		Users user=new Users();
		user.setUserId(userId);
		homepage.setUser(user);
		homepageRepository.save(homepage);	
		}

		return getHomepageDataByUserId(userId);
	}


	@Override
	public FirstMeetDto getFirstMeetDataByUserId(Integer userId) {
		logger.debug("get first meet data");

		Optional<FirstMeet> firstMeet= firstMeetRepository.findByUserId(userId);
		if(firstMeet.isPresent()) {
			return FirstMeetMapper.toDto(firstMeet.get());
		}
		else {
			FirstMeetDefault firstMeetDefault  = firstMeetDefaultRepository.getReferenceById(1);
			FirstMeetDto firstMeetDto = new FirstMeetDto();
			firstMeetDto.setTitle(firstMeetDefault.getTitle());
			firstMeetDto.setDescription(firstMeetDefault.getDescription());
			firstMeetDto.setEnabled(firstMeetDefault.isEnabled());
			return firstMeetDto;
		}
	}
	

	@Override
	public ProposalDto getProposalDataByUserId(Integer userId) {
		logger.debug("get proposal data");

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

	@Override
	public HomepageDto getHomepageDataByUserId(Integer userId) {
		logger.debug("get home page data");

		Optional<Homepage> homepage = homepageRepository.findByUserId(userId);
		if(homepage.isPresent()) {
			return HomepageMapper.toDto(homepage.get());
		}
		return null;
	}



}
