package com.security.bank.service;

import java.util.List;

import com.security.bank.dto.AdminDto;
import com.security.bank.entity.Account;
import com.security.bank.entity.AccountType;
import com.security.bank.entity.BranchType;
import com.security.bank.entity.User;

public interface AdminService {

	void add(AdminDto dto);

	List<User> getAllUser();

	User getUserByName(String username);

	String deleteUser(Long userId);

	String deactivate(Long userId, Long accountId);

	String activate(Long userId, Long accountId);

	List<Account> getActiveAccountsList();

	List<Account> getInActiveAccountsList();

	List<Account> getAccountsListByAccountType(AccountType accType);

	List<Account> getAccountsListByBranchType(BranchType branchType);

}
