package com.jeonghyeong.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jeonghyeong.service.UserService;



@RestController
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(path = "/user", method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestBody HashMap<String, String> map) {
		
		//exception1 : Cannot find certain parameter
				try {
					map.get("username");
					map.get("email");
					map.get("password");
				}catch (Exception e) {
					
					return new ResponseEntity<String>("Some Parameter is invalid", HttpStatus.BAD_REQUEST);
				}
				
				//exception2 : UserService Error
//				try {
					System.out.println("createStart");
					userService.userCreate(map.get("username"), map.get("email"), map.get("password"));
//				}catch(Exception e) {
//					
//					return new ResponseEntity<String>("Internal Server Error. Please Contact to Admin", HttpStatus.INTERNAL_SERVER_ERROR);
//				}
				
				return new ResponseEntity<String>("Request is complete. Please Login using user credentials", HttpStatus.OK);
		
	}
	
	
	
	/*
	 * TODO
	 * url : /user/me
	 * method : GET
	 * Request Header : ACCESS_TOKEN
	 * Response : ResponseEntity
	 * 
	 */
	@Secured({"ROLE_ADMIN","ROLE_USER", "USER"})
	@RequestMapping(path = "/user/me", method = RequestMethod.POST)
	public Authentication getUserProfile(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		return authentication;
	}
	
	@RequestMapping(path="qqqq")
	public String getUser() {
		return userService.getUser("maladroit1@gsitm.com");
	}

}
