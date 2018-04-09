package com.lucasasantiago.helpdesk.api.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lucasasantiago.helpdesk.api.entity.UserEntity;
import com.lucasasantiago.helpdesk.api.security.jwt.JwtUserFactory;
import com.lucasasantiago.helpdesk.api.service.UserService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService service;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserEntity user = service.findByEmail(email);
		
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user fount with username '%s'.", email));
		}else {
			return JwtUserFactory.create(user);
		}
	}

}
