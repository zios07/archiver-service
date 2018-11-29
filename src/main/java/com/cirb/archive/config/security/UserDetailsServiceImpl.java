package com.cirb.archive.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cirb.archive.domain.User;
import com.cirb.archive.service.IUserService;
import com.cirb.archive.service.exception.NotFoundException;
import com.google.common.collect.Lists;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private IUserService userService;

	public UserDetailsServiceImpl(IUserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;
		try {
			user = userService.findUserByUsername(username);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		
		if(user == null) 
			throw new UsernameNotFoundException("User not found with username : " + username);
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), Lists.newArrayList());

	}
}