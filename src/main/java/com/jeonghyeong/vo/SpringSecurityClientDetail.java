package com.jeonghyeong.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.jeonghyeong.entity.Client;
import com.jeonghyeong.entity.ClientAuthorizedGrantType;

@SuppressWarnings("deprecation")
public class SpringSecurityClientDetail implements ClientDetails{

	/**
	 * 
	 */
private static final long serialVersionUID = -5454527917673516898L;
	
	private Client client;
	
	private Set<String> clientGrantTypes = new HashSet<String>();
	
	public SpringSecurityClientDetail(Client client, List<ClientAuthorizedGrantType> clientGrantTypes) {
		this.client = client;
		System.out.println(clientGrantTypes.size());
		for(int index=0; index<clientGrantTypes.size(); index++) {
			System.out.println(clientGrantTypes.get(index).getClientGrantType());
			this.clientGrantTypes.add(clientGrantTypes.get(index).getClientGrantType());
		}
		
	}

	@Override
	public String getClientId() {
		return client.getClientId();
	}

	@Override
	public Set<String> getResourceIds() {
		return null;
	}

	@Override
	public boolean isSecretRequired() {
		return true;
	}

	@Override
	public String getClientSecret() {
		return client.getClientSecret();
	}

	@Override
	public boolean isScoped() {
		return true;
	}

	@Override
	public Set<String> getScope() {
		Set<String> clientScope = new HashSet<String>();
		clientScope.add(client.getScope());
		return clientScope;
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		
		return clientGrantTypes;
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
		Set<String> redirectUriSet = new HashSet<String>();
		redirectUriSet.add(client.getWebServerRedirectUri());
		return redirectUriSet;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
		authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorityList;
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		return client.getAccessTokenValidity();
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		return client.getRefreshTokenValidity();
	}

	@Override
	public boolean isAutoApprove(String scope) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		// TODO Auto-generated method stub
		return null;
	}
}

