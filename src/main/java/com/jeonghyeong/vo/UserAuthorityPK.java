package com.jeonghyeong.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserAuthorityPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1175910689964574908L;
	private String userId;
	private String authority;

}
