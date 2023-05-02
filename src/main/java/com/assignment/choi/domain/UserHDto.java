package com.assignment.choi.domain;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="USER_H_TB")
@Getter
@Setter
@IdClass(UserHDtoPK.class)
public class UserHDto {
	@Id
	@ManyToOne(targetEntity=HobbyDto.class, fetch=FetchType.LAZY)
	@JoinColumn(name="h_code_id")
	private HobbyDto hobbyDto; // A
	
	@Transient
	private String h_code_id;
	
	@Id
	@ManyToOne(targetEntity=UserDto.class)
	@JoinColumn(name="user_id")
	private UserDto userDto; // test
	
	@Transient
	private String userId;
	
//	@Override
//	public String toString() {
//		return "UserHDto [hobbyDto=" + hobbyDto.getH_code_id() + ", userDto=" + userDto.getUserId() + "]";
//	}

	
}
