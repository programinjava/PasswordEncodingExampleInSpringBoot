package com.programinjava.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.programinjava.learn.model.User;

public interface UserRepository  extends JpaRepository<User, Integer>{

	
	public User findByUsername(String username);
}
