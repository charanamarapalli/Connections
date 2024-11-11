package connections.connections_api.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import connections.connections_api.Service.Impl.MyUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired
	private MyUserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		logger.debug("Started Security Filter chain");
		http.csrf(a -> a.disable());
		http.authorizeHttpRequests(a->
		a.requestMatchers("api/login/**", "api/registerUser/**").permitAll()
		.anyRequest().authenticated());   //to make sure all requests are authorized
		//http.formLogin(Customizer.withDefaults());  // form login - Session gets stored
		http.httpBasic(Customizer.withDefaults());   // Basic authentication - No session stored, 
		// every time we send credentials (login user name and password) through authorization headers 
		http.sessionManagement(a -> a.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		//session is not needed- so every time we hit an request, new session creates.
		logger.debug("before jwt Filter chain");
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		// By default csrf, session management, authorize http request settings are different. So we changed. remaining we made it default.
		return http.build();
	}
	
	// below method gets UserDetailsService object but for it, we used already known inmemoryUserDetailsManager class.
	// Authentication provider is default
	/*
	 * @Bean public UserDetailsService userDetailsService() { UserDetails user1 =
	 * User .withDefaultPasswordEncoder() .username("tri") .password("test")
	 * .roles("ADMIN").build();
	 * 
	 * UserDetails user2 = User .withDefaultPasswordEncoder() .username("sha")
	 * .password("test1") .roles("ADMIN").build(); return new
	 * InMemoryUserDetailsManager(user1, user2); }
	 */
	 
	//This time we will create own class which implements UserDetailsService and use it in Authenticaton provider
	@Bean
	public AuthenticationProvider authenticationProvider() {
		logger.debug("In Authentication Provider");
		 DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		 provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		 provider.setUserDetailsService(userDetailsService);
		 return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
		logger.debug("In Authentication Manager");
		return authConfig.getAuthenticationManager();
	}
	
}
