package com.jeonghyeong.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jeonghyeong.entity.ClientAuthorizedGrantType;


public interface ClientAuthorizedGrantTypeRepository extends JpaRepository<ClientAuthorizedGrantType, String>{
	
	@Query("SELECT c FROM ClientAuthorizedGrantType c WHERE c.clientId = :clientId")
	List<ClientAuthorizedGrantType> getGrantTypeList(@Param ("clientId")  String clientId);

}
