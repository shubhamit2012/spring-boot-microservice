package com.draco.photoapp.api.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.draco.photoapp.api.users.service.UsersService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	Environment environment;

	@Autowired
	UsersService usersService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		System.out.println("Gateway IP: " + environment.getProperty("gateway.ip"));
		httpSecurity.csrf().disable();
		httpSecurity.authorizeRequests().antMatchers("/**").hasIpAddress(environment.getProperty("gateway.ip")).and()
				.addFilter(getAuthenticationFilter());
		httpSecurity.headers().frameOptions().disable();
	}

	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(usersService, environment,
				authenticationManager());
		// authenticationFilter.setAuthenticationManager(authenticationManager());
		// authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
		return authenticationFilter;
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usersService).passwordEncoder(bCryptPasswordEncoder);
	}

}
