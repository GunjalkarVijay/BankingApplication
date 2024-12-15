package com.security.bank.accounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.security.bank.dto.AccountDto;
import com.security.bank.dto.KycDto;
import com.security.bank.dto.NomineeDto;
import com.security.bank.entity.Account;
import com.security.bank.entity.Nominee;
import com.security.bank.entity.User;
import com.security.bank.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService service;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/create/{userId}")
	public void createAccount(@RequestBody AccountDto dto, @PathVariable Long userId) {
		service.createAccount(dto,userId);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/all/{userId}")
	public List<Account> getAllAccounts(@PathVariable Long userId){
		return service.getAllAccounts(userId);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/balance")
	public double getBalance(@RequestParam Long accountNumber) {
		return service.getBalance(accountNumber);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/nominee")
	public Nominee getNominee(@RequestParam Long accountNumber) {
		return service.getNominee(accountNumber);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/updateNominee/{accountId}")
	public void updateNominee(@RequestBody NomineeDto dto, @PathVariable Long accountId) {
		service.updateNominee(dto,accountId);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/getKycDetails")
	public User getKycDetails(@RequestParam Long accountNumber) {
		return service.getKycDetails(accountNumber);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/updateKyc/{accountId}")
	public User updateKyc(@RequestBody KycDto dto, @PathVariable Long accountId) {
		return service.updateKyc(dto, accountId);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/getAccount/summary")
	public Account getAccount(@RequestParam Long accountNumber) {
		return service.getAccount(accountNumber);
	}
	
}
