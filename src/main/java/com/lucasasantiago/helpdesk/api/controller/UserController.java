package com.lucasasantiago.helpdesk.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasasantiago.helpdesk.api.entity.UserEntity;
import com.lucasasantiago.helpdesk.api.response.Response;
import com.lucasasantiago.helpdesk.api.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<UserEntity>> create(HttpServletRequest request, @RequestBody UserEntity user,
			BindingResult result) {

		Response<UserEntity> response = new Response<>();

		try {
			validateCreateUser(user, result);

			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}

			user.setPassword(passwordEncoder.encode(user.getPassword()));
			response.setData(userService.createOrUpdate(user));

		} catch (DuplicateKeyException dE) {
			response.getErrors().add("Email already registered !");
			return ResponseEntity.badRequest().body(response);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	private void validateCreateUser(UserEntity user, BindingResult result) {
		if (user.getEmail() == null) {
			result.addError(new ObjectError("User", "Email no information"));
		}
	}

	@PutMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<UserEntity>> update(HttpServletRequest request, @RequestBody UserEntity user,
			BindingResult result) {
		Response<UserEntity> response = new Response<>();
		try {
			validateUpdate(user, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			UserEntity userPersisted = (UserEntity) userService.createOrUpdate(user);
			response.setData(userPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	private void validateUpdate(UserEntity user, BindingResult result) {
		if (user.getId() == null) {
			result.addError(new ObjectError("User", "Id no information"));
			return;
		}
		if (user.getEmail() == null) {
			result.addError(new ObjectError("User", "Email no information"));
			return;
		}
	}

	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<UserEntity>> findById(@PathVariable("id") String id) {
		Response<UserEntity> response = new Response<>();
		UserEntity user = userService.findById(id);
		if (user == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(user);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id) {
		Response<String> response = new Response<String>();
		UserEntity user = userService.findById(id);
		if (user == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		userService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}

	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<UserEntity>>> findAll(@PathVariable int page, @PathVariable int count) {
		Response<Page<UserEntity>> response = new Response<>();
		Page<UserEntity> users = userService.findAll(page, count);
		response.setData(users);
		return ResponseEntity.ok(response);
	}
}
