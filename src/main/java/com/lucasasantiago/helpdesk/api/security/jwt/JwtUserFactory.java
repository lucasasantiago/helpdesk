package com.lucasasantiago.helpdesk.api.security.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.lucasasantiago.helpdesk.api.entity.UserEntity;
import com.lucasasantiago.helpdesk.api.enumerator.ProfileEnum;

public class JwtUserFactory {
	
	private JwtUserFactory() {
		
	}
	
	public static JwtUser create(UserEntity user) {
		return new JwtUser(
				user.getId(),
				user.getEmail(),
				user.getPassword(),
				mapToGrantedAuthorities(user.getProfile()));
	}

	private static List<? extends GrantedAuthority> mapToGrantedAuthorities(ProfileEnum profile) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(profile.toString()));
		return authorities;
	}

}
