package com.security.service_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.entity.User;
import com.security.jwt_service.JwtService;
import com.security.repository.UserRepository;
import com.security.service.UseService;

@Service
public class UserServiceImpl implements UseService
{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JwtService jwtService;
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	
	@Override
	public User registerUser(User user)
	{
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public String verify(User user) 
	{
		System.out.println("UserService.verify()");
		Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		
		boolean authenticated = authenticate.isAuthenticated();
		if (authenticated) 
		{
			System.out.println("UserService.verify()-1");
			return jwtService.generateToken(user.getUsername());
		} 
		else 
		{
			System.out.println("UserService.verify()-2");
			return "Bad credentials";
		}
	}

}
