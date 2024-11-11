package connections.connections_api.Service;

public interface JwtService{

	String generateToken(String userEmail, Integer userId);
	 
}
