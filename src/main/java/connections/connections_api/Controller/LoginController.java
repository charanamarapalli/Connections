package connections.connections_api.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@GetMapping
	public String loginForm() {
		return "sai here";
	}
	
}
