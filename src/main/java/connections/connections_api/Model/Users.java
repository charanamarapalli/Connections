package connections.connections_api.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Users {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String userName;
	private String userEmail;
	private String password;
	
    public Users() {
    }
    
	public Users(int userId,String userEmail, String userName, String password) {
		this.userId = userId;
		this.userEmail=userEmail;
		this.userName = userName;
		this.password = password;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", password=" + password
				+ "]";
	}
	
	
	
	
	
	
	
	

}
