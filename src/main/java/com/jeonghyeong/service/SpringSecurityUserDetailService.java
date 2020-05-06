package com.jeonghyeong.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jeonghyeong.entity.User;
import com.jeonghyeong.entity.UserAuthority;
import com.jeonghyeong.repository.UserAuthorityRepository;
import com.jeonghyeong.repository.UserRepository;
import com.jeonghyeong.vo.SpringSecurityUserDetail;



@Service
public class SpringSecurityUserDetailService implements UserDetailsService{

	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserAuthorityRepository userAuthorityRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("Spring Security UserService is called");
		User queriedUser = userRepo.findbyUsername(username);
		List<UserAuthority> authorityList = userAuthorityRepo.getAuthorityList(queriedUser.getUserid());
		
		SpringSecurityUserDetail customUserDetail = new SpringSecurityUserDetail(queriedUser, authorityList);
		//유저네임(email)로 check -> 없으면 usernameNotFoundException
		
		//있으면,유저디테일 반환해야함. 이 userdetail을 user 및 authority를 생성자로 받아서 처리한 다음
		//반환하는 것이 좋을듯 하다.
		
		return customUserDetail;
	}



}
