package com.jeonghyeong.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name="user")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5623122908051843000L;

	@Id
    @Column(name="userid")
    private String userid;

    @Column(name="username")
    private String username;


    @Column(name="password")
    @NotNull
    private String password;

    @Column(name="email")
    @NotNull
    private String email;

    @Column(name="last_logined_date")
    @NotNull
    private Date lastLoginedDate;

    @Column(name="account_create_date")
    @NotNull
    private Date accountCreateDate;

    @Column(name="account_last_update_date")
    @NotNull
    private Date accountLastUpdateDate;

    @Column(name="credential_last_update_date")
    @NotNull
    private Date credentialLastUpdateDate;

    //비밀번호 일정 횟수 이상 틀렸는가?
    //5회 이상 잠금.
    @Column(name="login_failed_count")
    @NotNull
    private int loginFailedCount;

    //휴면 계정인가?
    @Column(name="is_account_enabled")
    @NotNull
    private boolean isAccountEnabled;


    @Column(name="is_account_expired")
    @NotNull
    private boolean isAccountExpired;

}
