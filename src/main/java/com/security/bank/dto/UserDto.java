package com.security.bank.dto;

import java.util.ArrayList;
import java.util.List;

import com.security.bank.entity.Account;
import com.security.bank.entity.Investment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private String name;
	private String username;
	private String password;
	private String address;
	private Long number;
	private String identityProof;
	private List<Account> accoutList = new ArrayList<>();
	private List<Investment> investmentList = new ArrayList<>();
}
