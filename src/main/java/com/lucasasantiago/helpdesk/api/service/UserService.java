package com.lucasasantiago.helpdesk.api.service;

import org.springframework.data.domain.Page;

import com.lucasasantiago.helpdesk.api.entity.UserEntity;

public interface UserService {
	
	UserEntity findByEmail(String email);

	UserEntity createOrUpdate(UserEntity user);

	UserEntity findById(String id);

	void delete(String id);

	Page<UserEntity> findAll(int page, int count);
}
