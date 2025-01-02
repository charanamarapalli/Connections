package connections.connections_api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import connections.connections_api.Service.ApplicantLinkService;
import connections.connections_api.Service.ProposalService;
import connections.connections_api.dto.FirstMeetDto;
import connections.connections_api.dto.HomepageDto;
import connections.connections_api.dto.ProposalDto;

@RestController
@RequestMapping("/api/public/")
public class PublicProposalContainer {
	
	@Autowired
	private ProposalService proposalService;
	
	@Autowired
	private ApplicantLinkService applicantLinkService;

	@GetMapping("/getFirstMeetData/link={token}")
	public ResponseEntity<FirstMeetDto> getFirstMeetData(@PathVariable String token) {
        Integer userId = applicantLinkService.getUserIdFromToken(token);
		return new ResponseEntity<>(proposalService.getFirstMeetDataByUserId(userId), HttpStatus.OK);
	}
	
	@GetMapping("/getHomePageData/link={token}")
	public ResponseEntity<HomepageDto> getHomePageData(@PathVariable String token) {
        Integer userId = applicantLinkService.getUserIdFromToken(token);
		return new ResponseEntity<>(proposalService.getHomepageDataByUserId(userId), HttpStatus.OK);
	}
	
	@GetMapping("/getProposalData/link={token}")
	public ResponseEntity<ProposalDto> getProposalData(@PathVariable String token) {
        Integer userId = applicantLinkService.getUserIdFromToken(token);
		return new ResponseEntity<>(proposalService.getProposalDataByUserId(userId), HttpStatus.OK);
	}
}
