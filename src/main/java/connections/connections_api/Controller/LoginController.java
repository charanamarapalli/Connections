package connections.connections_api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import connections.connections_api.Model.Users;
import connections.connections_api.Service.MyUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login")
public class LoginController {

	private Users users;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@GetMapping("/getCsrfToken")
	public CsrfToken getCsrfToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
	
	//csrf token is required by default in order to hit an api. Or else, i will get a 401 un-authorized
	@PostMapping("/registerUser")
	public String registerUser(@RequestBody Users user) {
		return myUserDetailsService.registerUser(user);
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestBody Users user) {
		return myUserDetailsService.verify(user);
	}
}
