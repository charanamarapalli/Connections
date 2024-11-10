package connections.connections_api.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import connections.connections_api.Entity.Users;
import connections.connections_api.Service.Impl.MyUserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private MyUserDetailsServiceImpl myUserDetailsService;
	
	@GetMapping("/getCsrfToken")
	public CsrfToken getCsrfToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
	
	//csrf token is required by default in order to hit an api. Or else, i will get a 401 un-authorized
	@PostMapping("/registerUser")
	public ResponseEntity<String> registerUser(@RequestBody Users user) {
		logger.debug("register user controller");
		return new ResponseEntity<>(myUserDetailsService.registerUser(user), HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody Users user) {
	    logger.debug("login user controller");
		return new ResponseEntity<>(myUserDetailsService.verify(user), HttpStatus.OK);
	}
	
	@GetMapping("/getCurrentStatus")
	public String getRandomText() {
		return "Hey";
	}
}
