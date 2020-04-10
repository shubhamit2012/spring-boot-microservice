package com.draco.photoapp.api.users.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.draco.photoapp.api.users.data.entity.UsersEntity;

public interface UsersRepository extends CrudRepository<UsersEntity, Long> {

}
