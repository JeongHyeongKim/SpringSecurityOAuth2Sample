package com.jeonghyeong.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.jeonghyeong.vo.ClientAuthorizedGrantTypePK;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="client_authorized_grant_type")
@IdClass(ClientAuthorizedGrantTypePK.class)
public class ClientAuthorizedGrantType {
	
	@Id
    @Column(name="client_id")
    private String clientId;

    @Id
    @Column(name="grant_type")
    private String clientGrantType;

}
