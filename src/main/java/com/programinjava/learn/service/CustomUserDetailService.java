package com.programinjava.learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.programinjava.learn.model.CustomUserDetails;
import com.programinjava.learn.model.User;
import com.programinjava.learn.repository.UserRepository;
@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		CustomUserDetails user = (CustomUserDetails) userRepository.findByUsername(username);
		if(user == null)
			throw new UsernameNotFoundException("User name not found ");
		return user;
	}




}



