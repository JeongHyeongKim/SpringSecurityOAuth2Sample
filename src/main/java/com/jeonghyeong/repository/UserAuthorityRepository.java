package com.jeonghyeong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jeonghyeong.entity.UserAuthority;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, String>{
	
	@Query("SELECT u FROM UserAuthority u WHERE u.userId = :userId")
	List<UserAuthority> getAuthorityList(@Param ("userId")  String userId);

}
