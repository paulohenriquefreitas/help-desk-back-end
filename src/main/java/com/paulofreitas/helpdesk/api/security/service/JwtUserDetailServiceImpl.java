package com.paulofreitas.helpdesk.api.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paulofreitas.helpdesk.api.entity.User;
import com.paulofreitas.helpdesk.api.security.jwt.JwtUserFactory;
import com.paulofreitas.helpdesk.api.service.UserService;

@Service
public class JwtUserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userService.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s.", email));
		}else {
			return JwtUserFactory.create(user);
		}
	}

}
