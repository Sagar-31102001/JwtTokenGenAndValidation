package com.security.rest_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.entity.User;
import com.security.service_impl.UserServiceImpl;

@RestController
public class UserContoller
{
	@Autowired
	private UserServiceImpl impl;
	
	@PostMapping("/register")
	public ResponseEntity<User> userRegistration(@RequestBody User user)
	{
		System.out.println("UserContoller.userRegistration()");
		User registerUser = impl.registerUser(user);
		
		return new ResponseEntity<User>(registerUser,HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody User user)
	{
		System.out.println("UserController.login()");
	    System.out.println("Received user: " + user.getUsername() + ", " + user.getPassword());
		return impl.verify(user);
	}
}
