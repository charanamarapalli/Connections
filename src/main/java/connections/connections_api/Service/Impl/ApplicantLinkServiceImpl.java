package connections.connections_api.Service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import connections.connections_api.Entity.ApplicantLink;
import connections.connections_api.Repository.ApplicantLinkRepository;
import connections.connections_api.Utils.Randomurlgenerator;
import connections.connections_api.Service.ApplicantLinkService;


@Service
public class ApplicantLinkServiceImpl implements ApplicantLinkService{

	@Autowired
	private ApplicantLinkRepository applicantLinkRepository;
	
	public String createApplicantLink(Integer userId) {
		String token = Randomurlgenerator.generateRandomUrl();
		ApplicantLink applicantLink = new ApplicantLink();
		applicantLink.setUserId(userId);
		applicantLink.setToken(token);
		applicantLinkRepository.save(applicantLink);
		
		return token;
	}
	
	public Integer getUserIdFromToken(String token) {
		 ApplicantLink applicantLink = applicantLinkRepository.findByToken(token)
	                .orElseThrow(() -> new RuntimeException("Token not found"));
	        return applicantLink.getUserId();
	}

	@Override
	public String getApplicantLink(Integer userId) {
		Optional<ApplicantLink> applicantLink = applicantLinkRepository.findByUserId(userId);
		return applicantLink.isPresent()?applicantLink.get().getToken():createApplicantLink(userId);
	}
}
