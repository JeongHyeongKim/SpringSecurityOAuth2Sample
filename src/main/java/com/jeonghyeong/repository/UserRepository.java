package com.jeonghyeong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jeonghyeong.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	@Query("SELECT u FROM User u WHERE u.username = :name")
	User findbyUsername(@Param ("name")  String name);

}
