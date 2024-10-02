package connections.connections_api.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import connections.connections_api.Service.MyUserDetailsService;


@RestController
public class SectionController {

    private static final Logger logger = LoggerFactory.getLogger(SectionController.class);

	@GetMapping("/getSessionId")
	public String loginForm() {
		logger.debug("get Session id");
		return "hey hi";
	}
	
}
