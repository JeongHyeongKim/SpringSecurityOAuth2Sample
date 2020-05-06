package com.jeonghyeong.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "client")
@Setter
@Getter
@NoArgsConstructor
public class Client implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6733243188585745158L;
	
	@Id
    @Column(name = "id")
    private String clientId;

    //product name
    @Column(name = "name")
    private String clientName;

    @Column(name = "secret")
    private String clientSecret;

    //이거도 분리해야함.
    @Column(name = "resource_ids")
    private String clientResourceIDs;

    @Column(name = "scope")
    private String scope;


    @Column(name = "redirect_uri")
    private String webServerRedirectUri;

    @Column(name = "access_token_validity")
    private int accessTokenValidity;

    @Column(name = "refresh_token_validity")
    private int refreshTokenValidity;


}
