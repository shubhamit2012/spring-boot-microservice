package com.draco.photoapp.api.users.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.draco.photoapp.api.users.data.dto.UsersDto;
import com.draco.photoapp.api.users.data.model.UserResponseModel;
import com.draco.photoapp.api.users.data.model.UsersRequest;
import com.draco.photoapp.api.users.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	UsersService userService;

	@Autowired
	Environment env;

	@RequestMapping("/status")
	public String status() {
		return "Hello User@" + env.getProperty("local.server.port");
	}

	@PostMapping
	public ResponseEntity<UserResponseModel> createUser(@Valid @RequestBody UsersRequest userRequest) {

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UsersDto userDto = mapper.map(userRequest, UsersDto.class);
		UsersDto createdUser = userService.createUser(userDto);
		UserResponseModel userResponse = mapper.map(createdUser, UserResponseModel.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
	}

}
