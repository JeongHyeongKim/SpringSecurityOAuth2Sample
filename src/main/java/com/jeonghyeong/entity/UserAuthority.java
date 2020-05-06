package com.jeonghyeong.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.jeonghyeong.vo.UserAuthorityPK;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name="user_authority")
@IdClass(UserAuthorityPK.class)
public class UserAuthority implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5071142288453067413L;

	@Id
	@Column(name="userid")
	String userId;
	
	
	@Id
	@Column(name="authority")
	String authority;
}

