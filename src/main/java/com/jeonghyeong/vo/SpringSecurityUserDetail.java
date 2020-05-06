package com.jeonghyeong.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jeonghyeong.entity.User;
import com.jeonghyeong.entity.UserAuthority;

public class SpringSecurityUserDetail implements UserDetails{
	

	private static final long serialVersionUID = 9142354985853034891L;

	private User user;
	
	private List<UserAuthority> authorityList;
	
	public SpringSecurityUserDetail(User user, List<UserAuthority> authorityList) {
		this.user = user;
		this.authorityList = authorityList;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> grantedAuthoritiyList = new ArrayList<GrantedAuthority>();
		System.out.println("spring security user detail");
		for(int i=0;i<authorityList.size()-1;i++) {
			System.out.println(authorityList.get(i).getAuthority());
			grantedAuthoritiyList.add(new SimpleGrantedAuthority(authorityList.get(i).getAuthority()));
		}
		
		return grantedAuthoritiyList;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return !user.isAccountExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		if(user.getLoginFailedCount()>4)
			return false;
		else
			return true;
	}

	//임의의 값으로, credential expire 기준을 비번 바꾼지 1달로 잡음.
	@Override
	public boolean isCredentialsNonExpired() {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD");
//		Date today = new Date();
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isAccountEnabled();
	}

}
