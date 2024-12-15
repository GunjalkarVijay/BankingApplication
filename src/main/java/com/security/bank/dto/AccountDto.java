package com.security.bank.dto;

import com.security.bank.entity.Nominee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

	private String accountType;
	private double balance;
	private String proof;
	private Nominee nominee;
}
