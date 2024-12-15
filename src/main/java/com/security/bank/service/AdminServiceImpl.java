package com.security.bank.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.bank.dto.AdminDto;
import com.security.bank.entity.Account;
import com.security.bank.entity.AccountType;
import com.security.bank.entity.BranchType;
import com.security.bank.entity.Role;
import com.security.bank.entity.User;
import com.security.bank.repository.AccountRepository;
import com.security.bank.repository.UserRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AccountRepository accRepo;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void add(AdminDto dto) {
		User user = new User();
		user.setAccountList(null);
		user.setAddress(dto.getAddress());
		user.setIdentityProof(dto.getIdentityProof());
		user.setInvestmentList(null);
		user.setName(dto.getName());
		user.setNumber(dto.getNumber());
		String encodedPassword = passwordEncoder.encode(dto.getPassword());
		user.setPassword(encodedPassword);
		user.setUsername(dto.getUsername());
		Role role = new Role();
		role.setRoleName("ROLE_ADMIN");
//		Set<Role> roles = user.getRoles();
//		roles.add(role);
		user.setRoles(role);
		userRepo.save(user);

	}

	@Override
	public List<User> getAllUser() {
		return userRepo.findAll();
	}

	@Override
	public User getUserByName(String username) {
		return userRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("user not found"));
	}

	@Override
	public String deleteUser(Long userId) {

		User user = userRepo.findById(userId).get();
		if(user == null) {
			return "Error in deletion";
		}
		userRepo.deleteById(userId);
		return "Deleted Successfully";
	}

	@Override
	public String deactivate(Long userId, Long accountId) {
		User user = userRepo.findById(userId).get();
		Account acc = accRepo.findById(accountId).get();
		if(user==null || acc==null || !user.getAccountList().contains(acc)) {
			return "ERROR";
		}
		acc.setStatus("INACTIVE");
		accRepo.save(acc);
		return "Deactivated Account for User with id: "+userId;
	}

	@Override
	public String activate(Long userId, Long accountId) {
		User user = userRepo.findById(userId).get();
		Account acc = accRepo.findById(accountId).get();
		if(user==null || acc==null || !user.getAccountList().contains(acc)) {
			return "ERROR";
		}
		acc.setStatus("ACTIVE");
		accRepo.save(acc);
		return "Activated Account for User with id: "+userId;
	}

	@Override
	public List<Account> getActiveAccountsList() {
		return accRepo.findAllActiveAccounts();
	}

	@Override
	public List<Account> getInActiveAccountsList() {
		return accRepo.findAllInActiveAccounts();
	}

	@Override
	public List<Account> getAccountsListByAccountType(AccountType accType) {
		return accRepo.findAllByAccountType(accType);
	}

	@Override
	public List<Account> getAccountsListByBranchType(BranchType branchType) {
		return accRepo.findAllByBranchType(branchType);
	}

}
