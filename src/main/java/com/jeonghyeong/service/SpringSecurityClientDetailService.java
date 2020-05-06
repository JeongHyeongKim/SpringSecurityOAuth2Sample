package com.jeonghyeong.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import com.jeonghyeong.entity.Client;
import com.jeonghyeong.entity.ClientAuthorizedGrantType;
import com.jeonghyeong.repository.ClientAuthorizedGrantTypeRepository;
import com.jeonghyeong.repository.ClientRepository;
import com.jeonghyeong.vo.SpringSecurityClientDetail;



@SuppressWarnings("deprecation")
@Service
@Transactional
public class SpringSecurityClientDetailService implements ClientDetailsService{
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private ClientAuthorizedGrantTypeRepository clientAuthGrantRepo;
	

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		System.out.println(clientId);
		Client client = clientRepo.findByClientId(clientId);
		List<ClientAuthorizedGrantType> clientGrantTypes = clientAuthGrantRepo.getGrantTypeList(clientId);
		
		SpringSecurityClientDetail clientDetail = new SpringSecurityClientDetail(client, clientGrantTypes);
		return clientDetail;
	}

}
