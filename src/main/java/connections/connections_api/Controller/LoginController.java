package connections.connections_api.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import connections.connections_api.Model.Users;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

	private List<Users> users = new ArrayList<>(Arrays.asList(new Users(1, "sai", "Test123")));
	
	@GetMapping("/getSessionId")
	public String loginForm(HttpServletRequest request) {
		return request.getSession().getId();
	}
	
	@GetMapping("/getCsrfToken")
	public CsrfToken getCsrfToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
	
	//csrf token is required by default in order to hit an api. Or else, i will get a 401 un-authorized
	@PostMapping("/addUser")
	public List<Users> login(@RequestBody Users user) {
		users.add(user);
		return users;
	}
}
