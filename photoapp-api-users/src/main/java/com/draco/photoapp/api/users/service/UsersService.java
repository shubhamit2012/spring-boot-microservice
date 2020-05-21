package com.draco.photoapp.api.users.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.draco.photoapp.api.users.data.dto.UsersDto;

public interface UsersService extends UserDetailsService {
	UsersDto createUser(UsersDto userDto);

	UsersDto getUserByEmail(String email);

}
