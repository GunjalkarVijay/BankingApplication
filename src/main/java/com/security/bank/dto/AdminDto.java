package com.security.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {

	private String name;
	private String username;
	private String password;
	private String address;
	private Long number;
	private String identityProof;
}
