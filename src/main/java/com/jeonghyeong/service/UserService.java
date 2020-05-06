package com.jeonghyeong.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jeonghyeong.entity.User;
import com.jeonghyeong.entity.UserAuthority;
import com.jeonghyeong.repository.UserAuthorityRepository;
import com.jeonghyeong.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserAuthorityRepository authorityRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	//TODO : 어떤 형태로 컨트롤러에게 성공했다는 것을 반환 할 것인가?
	public String userCreate(String username, String email, String password) {
		
		Date today = new Date();
		String randomUserId = UUID.randomUUID().toString();
		log.debug("UUID : " + randomUserId);
		
		User user = new User();
		UserAuthority userAuthority = new UserAuthority();
		
		user.setUserid(randomUserId);
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		user.setLoginFailedCount(0);
		user.setAccountCreateDate(today);
		user.setAccountLastUpdateDate(today);
		user.setCredentialLastUpdateDate(today);
		user.setLastLoginedDate(today);
		
		//�닔�젙�빐�빞�븿.
		user.setAccountEnabled(true);
		user.setAccountExpired(false);
		
		userAuthority.setUserId(randomUserId);
		userAuthority.setAuthority("ROLE_USER");
//		
//		try {
			System.out.println("saving start");
			userRepository.save(user);
			authorityRepository.save(userAuthority);

		return "";
	}
	
	
	public User userRead(String userId) {
		User user = new User();
		try {
			user = userRepository.getOne(userId);
		}catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		return user;
	}
	
	public String getUser(String username) {
		return userRepository.findbyUsername(username).getUsername();
	}
	
	//SELECT date_format(datetime, '%h:%i:%s') FROM `sandbox2` WHERE id=1
	
	
	
	
	

}
