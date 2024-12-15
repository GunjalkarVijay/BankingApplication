package com.security.bank.service;

import java.util.List;

import com.security.bank.dto.AccountDto;
import com.security.bank.dto.KycDto;
import com.security.bank.dto.NomineeDto;
import com.security.bank.entity.Account;
import com.security.bank.entity.Nominee;
import com.security.bank.entity.User;

public interface AccountService {

	void createAccount(AccountDto dto, Long userId);

	List<Account> getAllAccounts(Long userId);

	double getBalance(Long accountNumber);

	Nominee getNominee(Long accountNumber);

	void updateNominee(NomineeDto dto, Long accountId);

	User getKycDetails(Long accountNumber);

	User updateKyc(KycDto dto, Long accountId);

	Account getAccount(Long accountNumber);

}
