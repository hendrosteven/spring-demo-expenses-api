package com.kelaskoding.controller;

import java.util.Base64;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kelaskoding.entity.User;
import com.kelaskoding.params.LoginData;
import com.kelaskoding.params.ResponseData;
import com.kelaskoding.service.UserService;
import com.kelaskoding.utilty.MD5Generator;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginData data) {

		ResponseData response = new ResponseData();

		try {
			User user = userService.login(data.getEmail(), MD5Generator.generate(data.getPassword()));

			if (user == null) {
				response.setStatus(false);
				response.getMessages().add("Login gagal");
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
			} else {
				String baseStr = user.getEmail() + ":" + user.getPassword();
				String token = Base64.getEncoder().encodeToString(baseStr.getBytes());
				response.setStatus(true);
				response.getMessages().add("Login sukses!");
				response.setPayload(token);
				return ResponseEntity.ok(response);
			}
		} catch (Exception ex) {
			response.setStatus(false);
			response.getMessages().add("Errors: " + ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping
	public ResponseEntity<?> register(@Valid @RequestBody User user, Errors errors) {

		ResponseData response = new ResponseData();

		if (errors.hasErrors()) {
			for (ObjectError err : errors.getAllErrors()) {
				response.getMessages().add(err.getDefaultMessage());
			}
			response.setStatus(false);
			return ResponseEntity.ok(response);
		}

		try {
			user.setPassword(MD5Generator.generate(user.getPassword()));
			User output = userService.create(user);
			response.setPayload(output);
			response.setStatus(true);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			response.setStatus(false);
			response.getMessages().add("Errors: " + ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
