package com.security.bank.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.security.bank.dto.AdminDto;
import com.security.bank.entity.Account;
import com.security.bank.entity.AccountType;
import com.security.bank.entity.BranchType;
import com.security.bank.entity.User;
import com.security.bank.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService service;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add")
	public void add(@RequestBody AdminDto dto) {
		service.add(dto);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getAllUser")
	public List<User> getAllUser(){
		return service.getAllUser();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getUserByName/{username}")
	public User getUserByName(@PathVariable String username) {
		return service.getUserByName(username);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteUser/{userId}")
	public String deleteUser(@PathVariable Long userId) {
		return service.deleteUser(userId);
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/account/deactivate")
	public String deactivate(@RequestParam Long userId, @RequestParam Long accountId) {
		return service.deactivate(userId, accountId);
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/account/activate")
	public String activate(@RequestParam Long userId, @RequestParam Long accountId) {
		return service.activate(userId, accountId);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/account/getActiveAccountsList")
	public List<Account> getActiveAccountsList() {
		return service.getActiveAccountsList();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/account/getInActiveAccountsList")
	public List<Account> getInActiveAccountsList() {
		return service.getInActiveAccountsList();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
//	@GetMapping("/account/getAccountsList/{accountType}")
	@GetMapping("/accountList/ByAccountType/{accType}")
	public List<Account> getAccountsListByAccountType(@PathVariable AccountType accType) {
		return service.getAccountsListByAccountType(accType);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
//	@GetMapping("/account/getAccountsList/{branchType}")
	@GetMapping("/accountList/ByBranchType/{branchType}")
	public List<Account> getAccountsListByBranchType(@PathVariable BranchType branchType) {
		return service.getAccountsListByBranchType(branchType);
	}
	
	
	
	
	

}
