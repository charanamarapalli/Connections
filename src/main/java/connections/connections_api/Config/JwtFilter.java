package connections.connections_api.Config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import connections.connections_api.Service.JwtService;
import connections.connections_api.Service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	ApplicationContext context;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		String jwtToken = null;
		String userEmail =null;
		
		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
			jwtToken = authHeader.substring(7);
			userEmail = jwtService.extractUserEmail(jwtToken);
		}
		
		if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(userEmail);
			if(jwtService.validateToken(jwtToken, userDetails)){
				UsernamePasswordAuthenticationToken upatToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				upatToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(upatToken);
			}
		}
		
		filterChain.doFilter(request, response);
		
	}

}
