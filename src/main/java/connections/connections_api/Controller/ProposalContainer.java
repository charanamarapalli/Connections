package connections.connections_api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import connections.connections_api.Service.ProposalService;
import connections.connections_api.dto.AllProposalDataDto;
import connections.connections_api.dto.FirstMeetDto;
import connections.connections_api.dto.ProposalDto;

@RestController
@RequestMapping("/api/")
public class ProposalContainer {

	@Autowired
	private ProposalService proposalService;
	
	@GetMapping("/getAllProposalData/userId={userId}")
	public ResponseEntity<AllProposalDataDto> getAllProposalData(@PathVariable Integer userId) {
		return new ResponseEntity<>(proposalService.getAllProposalDataByUserId(userId), HttpStatus.OK);
	}
	
	@PostMapping("/saveFirstMeetData/userId={userId}")
	public ResponseEntity<AllProposalDataDto> saveFirstMeetData(@RequestBody FirstMeetDto firstMeetDto, @PathVariable Integer userId) {
		return new ResponseEntity<>(proposalService.saveFirstMeetDataByUserId(userId, firstMeetDto), HttpStatus.OK);
	}
	
	@PostMapping("/saveProposalData/userId={userId}")
	public ResponseEntity<AllProposalDataDto> saveProposalData(@RequestBody ProposalDto proposalDto, @PathVariable Integer userId) {
		return new ResponseEntity<>(proposalService.saveProposalDataByUserId(userId, proposalDto), HttpStatus.OK);
	}
	
	
	
}
