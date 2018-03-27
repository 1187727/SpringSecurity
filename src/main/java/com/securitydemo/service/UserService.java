package com.securitydemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.securitydemo.data.UserRepository;
import com.securitydemo.model.User;
import com.securitydemo.model.UserPrincipal;

public class UserService implements UserDetailsService {
	
	@Autowired
	UserRepository repo;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = repo.findByUsername(username);
		return new UserPrincipal(user.getUsername(),user.getPassword(),user.getRole().toArray(new String[user.getRole().size()]));
	}

	
}
