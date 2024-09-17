package connections.connections_api.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(a -> a.disable());
		http.authorizeHttpRequests(a-> a.anyRequest().authenticated());   //to make sure all requests are authorized
		http.formLogin(Customizer.withDefaults());  // form login - Session gets stored
		http.httpBasic(Customizer.withDefaults());   // Basic authentication - No session stored, 
		// every time we send credentials (login user name and password) through authorization headers 
		http.sessionManagement(a -> a.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		//session is not needed- so every time we hit an request, new session creates.
		
		// By default csrf, session management, authorize http request settings are different. So we changed. remaining we made it default.
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user1 = User
				.withDefaultPasswordEncoder()
				.username("tri")
				.password("test")
				.roles("ADMIN").build();
				
		UserDetails user2 = User
				.withDefaultPasswordEncoder()
				.username("sha")
				.password("test1")
				.roles("ADMIN").build();
		return new InMemoryUserDetailsManager(user1, user2);
	}
}
