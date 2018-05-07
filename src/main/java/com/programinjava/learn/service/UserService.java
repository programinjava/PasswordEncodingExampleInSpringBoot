package com.programinjava.learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.programinjava.learn.model.User;
import com.programinjava.learn.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	@Bean
	public PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	public User save(User user) {
//		Encoding the password
		user.setPassword(getEncoder().encode(user.getPassword()));
		return userRepository.save(user);
		
		
	}
	
	
//	this method is for match the password with entered one
	public boolean matchDbPasswordWithEntered(String username,String enteredPassword) {
		
		User user  = userRepository.findByUsername(username);
		if(user == null)
			throw new UsernameNotFoundException("user not found");
		if(getEncoder().matches(enteredPassword,user.getPassword())) {
			System.out.println("Password Match");
//			can change the passwrod as well and store in db
			return true;
		}else {
//			can throw the exception
			System.out.println("Password not matched");
			return false;
		}
	}
	
	

}
