package com.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.entity.User;
import com.security.repository.UserRepository;

@Service
public class MyUserDetailsSrvice implements UserDetailsService
{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		User user = userRepository.findByUsername(username);
		System.out.println(user);
		if (user==null)
		{
			System.out.println("User Not Found...");
			 throw new UsernameNotFoundException("User Does Not Exists In DB");
		}
	
		return new UserPrincipal(user);
	}

}
