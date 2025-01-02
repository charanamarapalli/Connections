package connections.connections_api.Service;

public interface ApplicantLinkService {
	public String createApplicantLink(Integer userId);

	public Integer getUserIdFromToken(String token);
	
	public String getApplicantLink(Integer userId);
}
