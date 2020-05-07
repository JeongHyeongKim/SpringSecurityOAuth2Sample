package com.jeonghyeong.controller;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jeonghyeong.service.UserService;



@RestController
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	
	
	@RequestMapping(path = "/user", method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestBody(required=false) HashMap<String, String> map) {
		
		//exception1 : Cannot find certain parameter
				try {
					map.get("username");
					map.get("email");
					map.get("password");
				}catch (Exception e) {
					
					return new ResponseEntity<String>("Some Parameter is invalid", HttpStatus.BAD_REQUEST);
				}
				

				try {
					userService.userCreate(map.get("username"), map.get("email"), map.get("password"));
				}catch(Exception e) {
					
					return new ResponseEntity<String>("Internal Server Error. Please Contact to Admin", HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
				return new ResponseEntity<String>("Request is complete. Please Login using user credentials", HttpStatus.OK);
		
	}
	
	
	

	
	
	@RequestMapping(path = "/user/me", method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<Object> getUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		return new ResponseEntity<Object>(authentication.getPrincipal(), HttpStatus.OK);
	}
	
	
	
	
	
	
	@RequestMapping(path = "/users", method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<String> getUserList(){

		
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
	
	
	
	
	
	
	@RequestMapping(path = "/user/me", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeUser(@RequestBody HashMap<String, String> map) throws ParseException{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		JSONParser parser = new JSONParser();
		JSONObject principal = (JSONObject) parser.parse((String)authentication.getPrincipal());
		
		if(userService.removeUser((String) principal.get("username"))) {
			return new ResponseEntity<String>("Remove user is success", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("There is no user", HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	
	
	
	
	
	
	@RequestMapping(path = "/user/me", method = RequestMethod.PUT)
	public ResponseEntity<String> updateUser(@RequestBody HashMap<String, String> map) throws ParseException{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		JSONParser parser = new JSONParser();
		JSONObject principal = (JSONObject) parser.parse((String)authentication.getPrincipal());
		
		//accessToken user, 입력한 user 비교
		
		if(userService.updateUser(map)!=null) {
			return new ResponseEntity<String>("Remove user is success", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("There is no user", HttpStatus.NOT_FOUND);
		}
		
	}
	
	
}
