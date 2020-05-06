package com.jeonghyeong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jeonghyeong.entity.Client;

public interface ClientRepository extends JpaRepository<Client, String>{
	@Query("SELECT c FROM Client c WHERE c.clientId = :clientId")
	Client findByClientId(@Param ("clientId") String clientId);

}
