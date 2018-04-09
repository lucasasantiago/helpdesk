package com.lucasasantiago.helpdesk;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lucasasantiago.helpdesk.api.entity.UserEntity;
import com.lucasasantiago.helpdesk.api.enumerator.ProfileEnum;
import com.lucasasantiago.helpdesk.api.repository.UserRepository;

@SpringBootApplication
public class HelpDeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpDeskApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			initUsers(userRepository, passwordEncoder);
		};
	}

	private void initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		UserEntity admin = new UserEntity();
		admin.setEmail("admin@helpdesk.com");
		admin.setPassword("123456");
		admin.setProfile(ProfileEnum.ROLE_ADMIN);

		UserEntity user = userRepository.findByEmail("admin@helpdesk.com");
		if (user == null) {
			userRepository.save(admin);
		}
	}
}
