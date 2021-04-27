package org.bos.Achaoub.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.userdetails.User;
import org.bos.Achaoub.SpringApplicationContext;
import org.bos.Achaoub.models.requests.UserLoginRequest;
import org.bos.Achaoub.services.UserService;
import org.bos.Achaoub.shared.dto.UserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthentificationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authentificationManager;
	

	public AuthentificationFilter(AuthenticationManager authentificationManager) {
		this.authentificationManager = authentificationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			UserLoginRequest creds = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequest.class);
			return authentificationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub

		String userName =((User) authResult.getPrincipal()).getUsername();

		//UserPrincipal principal =(UserPrincipal) authResult.getPrincipal();
		
		UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");

		UserDto userDto = userService.getUser(userName);

		String token = Jwts.builder().setSubject(userName).claim("id", userDto.getUserId())
				.claim("name", userDto.getNom() + " " + userDto.getPrenom())
				.claim("role",userDto.getRole())
				.claim("auth", userDto.getRoleID())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET).compact();

		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		response.addHeader("user_id", userDto.getUserId());
		response.addHeader("user_Email", userDto.getEmail());

		response.getWriter().write("{\"token\": \"" + token + "\", \"id\": \"" + userDto.getUserId() + "\"}");
	}

}
