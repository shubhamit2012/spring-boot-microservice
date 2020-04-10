package com.draco.photoapp.api.users.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.draco.photoapp.api.users.data.dto.UsersDto;
import com.draco.photoapp.api.users.data.entity.UsersEntity;
import com.draco.photoapp.api.users.data.repository.UsersRepository;
import com.draco.photoapp.api.users.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	UsersRepository usersRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UsersServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.usersRepository = usersRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UsersDto createUser(UsersDto userDto) {
		userDto.setUserId(UUID.randomUUID().toString());
		userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UsersEntity usersEntity = mapper.map(userDto, UsersEntity.class);
		usersRepository.save(usersEntity);
		UsersDto usersDto = mapper.map(usersEntity, UsersDto.class);
		return usersDto;
	}

}
