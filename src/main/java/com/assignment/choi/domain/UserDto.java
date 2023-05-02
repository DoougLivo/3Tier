package com.assignment.choi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="USER_TB")
@Getter
@Setter
public class UserDto {
	@Id
	@Column(name="user_id", length=20)
	private String userId;
	
	@Column(name="user_nm", length=20, nullable=false)
	private String userNm;

	@Column(name="user_email", length=40, nullable=false, unique = true)
	private String user_email;

	@ManyToOne(targetEntity = DepDto.class, fetch = FetchType.LAZY)
	@JoinColumn(name="dep_id")
	private DepDto depDto;
	
	@Column(name="user_phone", length=13, unique = true)
	private String user_phone;
	
	@Column(name="user_addr", length=50)
	private String user_addr;
	
	@Column(name="sign_yn", length=1)
	private String signYn;

//	@Override
//	public String toString() {
//		return "UserDto [userId=" + userId + ", userNm=" + userNm + ", user_email=" + user_email + ", depDto=" + depDto
//				+ ", user_phone=" + user_phone + ", user_addr=" + user_addr + ", signYn=" + signYn + "]";
//	}
	
}
