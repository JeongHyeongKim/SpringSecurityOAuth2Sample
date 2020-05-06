package com.jeonghyeong.controller;

import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jeonghyeong.service.ClientService;

@RestController
public class ClientController {
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@RequestMapping(path="/client", method=RequestMethod.POST)
	public ResponseEntity<String> clientCreate(@RequestBody HashMap<String, String> map){
		
		//object not found exception
		
		try {
			clientService.clientCreate(map.get("clientName"), map.get("redirectUrl"));
		}catch (Exception e) {
			return new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
		return new ResponseEntity<String>("Client is Created.", HttpStatus.OK);
		
		
	}
	
	
	@RequestMapping("/hash")
	public String encodingClientKeyValue() {
		String clientId = "7lXur307kI8Iw47";
		String clientSecret = "nQGx09uKRs09O1163IG0";
		
		String credential = clientId+":"+clientSecret;
		//String encodedCredential = new String(Base64.encode(credential.getBytes()));
		String encodedCredential = new String (Base64.encodeBase64(credential.getBytes()));
		
		
		return encodedCredential;
	}
	
	@RequestMapping("/asdf")
	public String getenCodedString() {
		return passwordEncoder.encode("nQGx09uKRs09O1163IG0");
		
	}

}
