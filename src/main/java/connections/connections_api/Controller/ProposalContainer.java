package connections.connections_api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import connections.connections_api.Service.ApplicantLinkService;
import connections.connections_api.Service.ProposalService;
import connections.connections_api.dto.AllProposalDataDto;
import connections.connections_api.dto.FirstMeetDto;
import connections.connections_api.dto.ProposalDto;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/")
public class ProposalContainer {

	@Autowired
	private ProposalService proposalService;
	
	@Autowired
	private ApplicantLinkService applicantLinkService;
	
	@GetMapping("/generateLink")
	public ResponseEntity<String> createApplicantLink(HttpServletRequest request) {
	    Integer userId = (Integer) request.getAttribute("userId");
	    String link = applicantLinkService.getApplicantLink(userId);
		return new ResponseEntity<>("Link Generated Successfully: "+link, HttpStatus.OK);
	}
	
	@GetMapping("/getAllProposalData")
	public ResponseEntity<AllProposalDataDto> getAllProposalData(HttpServletRequest request) {
	    Integer userId = (Integer) request.getAttribute("userId");
		return new ResponseEntity<>(proposalService.getAllProposalDataByUserId(userId), HttpStatus.OK);
	}
	
	@GetMapping("/getFirstMeetData")
	public ResponseEntity<FirstMeetDto> getFirstMeetData(HttpServletRequest request) {
		Integer userId = (Integer) request.getAttribute("userId");
		return new ResponseEntity<>(proposalService.getFirstMeetDataByUserId(userId), HttpStatus.OK);
	}
	
	@GetMapping("/getProposalData")
	public ResponseEntity<ProposalDto> getProposalData(HttpServletRequest request) {
		Integer userId = (Integer) request.getAttribute("userId");
		return new ResponseEntity<>(proposalService.getProposalDataByUserId(userId), HttpStatus.OK);
	}

	@PostMapping("/saveFirstMeetData")
	public ResponseEntity<FirstMeetDto> saveFirstMeetData(@RequestBody FirstMeetDto firstMeetDto, HttpServletRequest request) {
		Integer userId = (Integer) request.getAttribute("userId");
		return new ResponseEntity<>(proposalService.saveFirstMeetDataByUserId(userId, firstMeetDto), HttpStatus.OK);
	}

	@PostMapping("/saveProposalData")
	public ResponseEntity<ProposalDto> saveProposalData(@RequestBody ProposalDto proposalDto, HttpServletRequest request) {
		Integer userId = (Integer) request.getAttribute("userId");
		return new ResponseEntity<>(proposalService.saveProposalDataByUserId(userId, proposalDto), HttpStatus.OK);
	}

}
