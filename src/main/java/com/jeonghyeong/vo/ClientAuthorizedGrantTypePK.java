package com.jeonghyeong.vo;

import java.io.Serializable;

import lombok.Data;


@Data
public class ClientAuthorizedGrantTypePK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9144394059643463699L;
	
	private String clientId;
	private String clientGrantType;

}
