package com.draco.photoapp.api.users.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.draco.photoapp.api.users.data.dto.UsersDto;
import com.draco.photoapp.api.users.data.model.LoginRequestModel;
import com.draco.photoapp.api.users.service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	UsersService usersService;

	Environment environment;

	public AuthenticationFilter(UsersService usersService, Environment environment,
			AuthenticationManager authenticationManager) {
		this.usersService = usersService;
		this.environment = environment;
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			LoginRequestModel creds = new ObjectMapper().readValue(request.getInputStream(), LoginRequestModel.class);
			return getAuthenticationManager()
					.authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword()));
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String userName = ((User) auth.getPrincipal()).getUsername();
		UsersDto usersDto = usersService.getUserByEmail(userName);
		String jwtToken = Jwts.builder().setSubject(usersDto.getUserId())
				.setExpiration(new Date(
						System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
				.signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret")).compact();
		response.setHeader("authToken", jwtToken);
		response.setHeader("userId", usersDto.getUserId());
	}

}
