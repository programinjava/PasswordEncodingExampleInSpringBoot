package com.programinjava.learn.controller;


import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programinjava.learn.model.User;
import com.programinjava.learn.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserController.
 */
@RestController
@RequestMapping("/")
public class UserController {

	/** The user service. */
	@Autowired
	UserService userService;


	/**
	 * Creates the user.
	 *
	 * @param user the user
	 * @param errors the errors
	 * @param request the request
	 * @return the response entity
	 */
	@PostMapping("/user")
	public ResponseEntity<Void> createUser(@RequestBody @Valid User user ,BindingResult errors , HttpServletRequest request){
		if(errors.hasErrors()) {
			return ResponseEntity.badRequest().build();			
		}

		User user1 =userService.save(user);
		return ResponseEntity.created(URI.create(user1.getId().toString())).build();
	}
	
	/**
	 * Change password.
	 *
	 * @param user the user
	 * @param errors the errors
	 * @param request the request
	 * @return the response entity
	 */
	@PutMapping("/user")
	public ResponseEntity<Boolean> changePassword(@RequestBody @Valid User user ,BindingResult errors , HttpServletRequest request){
		if(errors.hasErrors()) {
			return ResponseEntity.badRequest().build();			
		}

		if(userService.matchDbPasswordWithEntered(user.getUsername(), user.getPassword()))
			return ResponseEntity.ok(true);
		return ResponseEntity.ok(false);
	}


}
