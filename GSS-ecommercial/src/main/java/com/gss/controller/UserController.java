package com.gss.controller;

import com.gss.service.OktaService;
import com.okta.sdk.resource.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
	private final OktaService oktaService;

	@Autowired
	public UserController(OktaService oktaService) {
		this.oktaService = oktaService;
	}

	@PostMapping("/register")
	public User registerUser(@RequestBody UserRequest userRequest) {
		return oktaService.createUser(
				userRequest.getFirstName(),
				userRequest.getLastName(), 
				userRequest.getEmail(),
				userRequest.getPassword()
				);
	}

	public static class UserRequest { 
		private String firstName; 
		private String lastName; 
		private String email; 
		private String password; // Getters và setters }
		public String getFirstName() {
			// TODO Auto-generated method stub
			return null;
		}
		public String getLastName() {
			// TODO Auto-generated method stub
			return null;
		}
		public String getPassword() {
			// TODO Auto-generated method stub
			return null;
		}
		public String getEmail() {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
