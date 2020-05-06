package com.jeonghyeong.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeonghyeong.entity.Client;
import com.jeonghyeong.entity.ClientAuthorizedGrantType;
import com.jeonghyeong.repository.ClientAuthorizedGrantTypeRepository;
import com.jeonghyeong.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private ClientAuthorizedGrantTypeRepository authGrantTypeRepo;
	
	
	public String clientCreate(String clientName, String rediredtUrl) {
		
		Client client = new Client();
		String clientId = this.randomStringGenerator(15);
		ClientAuthorizedGrantType authorizationCode = new ClientAuthorizedGrantType();
		ClientAuthorizedGrantType refreshToken = new ClientAuthorizedGrantType();
		
		
		client.setClientName(clientName);
		client.setClientId(clientId);
		client.setClientSecret(randomStringGenerator(20));
		client.setAccessTokenValidity(360);
		client.setRefreshTokenValidity(3600);
		client.setWebServerRedirectUri(rediredtUrl);
		
		
		authorizationCode.setClientId(clientId);
		authorizationCode.setClientGrantType("authorization_code");
		refreshToken.setClientId(clientId);
		refreshToken.setClientGrantType("refresh_token");
		
//		client.setClientResourceIDs(clientResourceIDs);
//		client.setScope(scope);
//		client.setAuthorizedGrantTypes(authorizedGrantTypes);
		
		
//		static AuthorizationGrantType	CLIENT_CREDENTIALS 
//		static AuthorizationGrantType	IMPLICIT
//		static AuthorizationGrantType	PASSWORD 
//		static AuthorizationGrantType	REFRESH_TOKEN 
		
		clientRepo.save(client);
		authGrantTypeRepo.save(authorizationCode);
		authGrantTypeRepo.save(refreshToken);
		
		
		return "";
	}
	
	
	private String randomStringGenerator(int length) {

		StringBuffer clientSecret = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < length; i++) {
		    int rIndex = rnd.nextInt(3);
		    switch (rIndex) {
		    case 0:
		        clientSecret.append((char) ((rnd.nextInt(26)) + 97));
		        break;
		    case 1:
		        clientSecret.append((char) ((rnd.nextInt(26)) + 65));
		        break;
		    case 2:
		        clientSecret.append((rnd.nextInt(10)));
		        break;
		    }
		}
		return new String(clientSecret);
	}


}
