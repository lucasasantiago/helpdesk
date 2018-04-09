package com.lucasasantiago.helpdesk.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.lucasasantiago.helpdesk.api.entity.UserEntity;
import com.lucasasantiago.helpdesk.api.repository.UserRepository;
import com.lucasasantiago.helpdesk.api.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserEntity findByEmail(String email) {
		return this.repository.findByEmail(email);
	}

	@Override
	public UserEntity createOrUpdate(UserEntity user) {
		return this.repository.save(user);
	}

	@Override
	public UserEntity findById(String id) {
		return this.repository.findById(id).orElseGet(null);
	}

	@Override
	public void delete(String id) {
		this.repository.deleteById(id);
	}

	@Override
	public Page<UserEntity> findAll(int page, int count) {
		return this.repository.findAll(PageRequest.of(page, count));
	}

}
