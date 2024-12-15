package com.security.bank.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.bank.dto.AccountDto;
import com.security.bank.dto.KycDto;
import com.security.bank.dto.NomineeDto;
import com.security.bank.entity.Account;
import com.security.bank.entity.AccountType;
import com.security.bank.entity.BranchType;
import com.security.bank.entity.Card;
import com.security.bank.entity.CardType;
import com.security.bank.entity.Nominee;
import com.security.bank.entity.User;
import com.security.bank.repository.AccountRepository;
import com.security.bank.repository.CardRepository;
import com.security.bank.repository.NomineeRepository;
import com.security.bank.repository.UserRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accRepo;

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	NomineeRepository nomineeRepo;
	
	@Autowired
	CardRepository cardRepo;
	
//	public String randomGenerator(int size) {
//		String ans ="";
//		for(int i=0; i<size; i++) {
//			ans+= (Math.random()*10);
//		}
//		return ans;
//	}
public String generateCardNumber() {
	StringBuilder cardNumber = new StringBuilder();
	Random random = new Random();

	// Generating a 16-digit card number
	for (int i = 0; i < 16; i++) {
		int digit = random.nextInt(10);
		cardNumber.append(digit);
	}
	return cardNumber.toString();
}

	public int generateCvv() {
		Random random = new Random();
		// Generating a random 3-digit number for CVV
		return random.nextInt(900) + 100;
	}

	@Override
	public void createAccount(AccountDto dto, Long userId) {
//		User user = userRepo.findById(userId).orElseThrow(()-> new RuntimeException("no user found with id"));
//		Account acc = new Account();
//		acc.setBalance(dto.getBalance());
//		acc.setNominee(dto.getNominee());
//		acc.setProof(dto.getProof());
//		acc.setStatus("ACTIVE");
//		String accType = dto.getAccountType();
//		AccountType accountType;
//		BranchType branch;
//		Card card = new Card();
//		card.setCardHolderName(user.getName());
//		card.setAllocationDate(new Date());
//		card.setCardNumber(Long.parseLong(generateCardNumber()));
//		card.setCvv(generateCvv());
//
//		//setting expiry date
//		Calendar calender = Calendar.getInstance();
//		calender.setTime(new Date());
//		calender.add(Calendar.YEAR, 5);
//		card.setExpiryDate(calender.getTime());
//
//		card.setPin(1234L);
//		card.setStatus("ACTIVE");
//		CardType cardType;
//		switch(accType) {
//		case "SAVINGS": {
//			accountType = AccountType.SAVINGS;
//			branch = BranchType.BOB;
//			cardType = CardType.DEBIT_GLOBAL;
//			card.setCardType(cardType);
//			card.setDailyLimit(40000);
//			acc.setInterestRate(2.70f);
//			cardRepo.save(card);
//			break;
//		}
//		case "CURRENT":{
//			accountType = AccountType.CURRENT;
//			branch = BranchType.ICIC;
//			cardType = CardType.CREDIT_PREMIUM;
//			card.setCardType(cardType);
//			card.setDailyLimit(50000);
//			acc.setInterestRate(5.2f);
//			cardRepo.save(card);
//			break;
//		}
//		case "PPF":{
//			accountType = AccountType.PPF;
//			branch = BranchType.SBI;
//			card = null;
////			cardType = CardType.DEBIT_GLOBAL;
////			card.setCardType(cardType);
////			card.setDailyLimit(40000);
//			acc.setInterestRate(7.4f);
//			break;
//		}
//		case "SALARY":{
//			accountType = AccountType.SALARY;
//			branch = BranchType.HDFC;
//			cardType = CardType.CREDIT_MASTER;
//			card.setCardType(cardType);
//			card.setDailyLimit(75000);
//			acc.setInterestRate(4.1f);
//			cardRepo.save(card);
//			break;
//		}
//		default :{
//			throw new RuntimeException("mismatched account type");
//		}
//		}
//
//		acc.setAccountNumber(generateRandomNumber());
//		acc.setAccountType(accountType);
//		acc.setBranch(branch);
//		acc.setCard(card);
//		acc.setOpeningDate(new Date());
//		acc.setUser(user);
//		List<Account> accountList = user.getAccountList();
//		accountList.add(acc);
//		user.setAccountList(accountList);
//		userRepo.save(user);
//		accRepo.save(acc);

		User user = new User();
		if(userRepo.existsById(userId)){
			user = userRepo.findById(userId).get();
		}
		Account account = new Account();
		Card card = new Card();
		card.setCardNumber(Long.parseLong(generateCardNumber()));
		card.setCvv(generateCvv());
		card.setCardHolderName(user.getName());
		card.setStatus("ACTIVE");
		card.setPin(1122L);
		card.setAllocationDate(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, 5);
		card.setExpiryDate(calendar.getTime());

		switch(dto.getAccountType()){
			case "SAVINGS":{
				card.setCardType(CardType.DEBIT_GLOBAL);
				card.setDailyLimit(40000);
				cardRepo.save(card);
				account.setAccountType(AccountType.SAVINGS);
				account.setInterestRate(2.70F);
				account.setBranch(BranchType.BOB);
				account.setCard(card);
				break;
			}
			case "CURRENT":{
				card.setCardType(CardType.CREDIT_PREMIUM);
				card.setDailyLimit(50000);
				cardRepo.save(card);
				account.setAccountType(AccountType.CURRENT);
				account.setBranch(BranchType.ICIC);
				account.setCard(card);
				account.setInterestRate(5.2F);
				break;
			}
			case "PPF":{
				account.setAccountType(AccountType.PPF);
				account.setBranch(BranchType.SBI);
				account.setInterestRate(7.4F);
				break;
			}
			case "SALARY":{
				card.setCardType(CardType.CREDIT_MASTER);
				card.setDailyLimit(75000);
				cardRepo.save(card);
				account.setAccountType(AccountType.SALARY);
				account.setBranch(BranchType.HDFC);
				account.setCard(card);
				account.setInterestRate(4.1F);
				break;
			}
			default:{
				throw  new RuntimeException("No AccountType Selected");
			}
		}
		account.setAccountNumber(generateRandomNumber());
		account.setStatus("ACTIVE");
		account.setBalance(dto.getBalance());
		Nominee nominee = nomineeRepo.save(dto.getNominee());
		account.setNominee(nominee);
		account.setProof(dto.getProof());
		account.setOpeningDate(new Date());
		account.setUser(user);
		accRepo.save(account);
	}

	public Long generateRandomNumber() {
		Random random = new Random();
		// Generate a random number between 10000000 and 99999999 (8-digit number)
		return 10000000 + random.nextLong(90000000);
	}

	@Override
	public List<Account> getAllAccounts(Long userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new RuntimeException("no user found"));
		return user.getAccountList();
	}

	@Override
	public double getBalance(Long accountNumber) {
		Account account = accRepo.findByAccountNumber(accountNumber).orElseThrow(()-> new RuntimeException("No account found with id"));
		return account.getBalance();
	}

	@Override
	public Nominee getNominee(Long accountNumber) {
		Account account = accRepo.findByAccountNumber(accountNumber).orElseThrow(()-> new RuntimeException("No account found with id"));
		return account.getNominee();
	}

	@Override
	public void updateNominee(NomineeDto dto, Long accountId) {
//		Account account = accRepo.findById(accountId).orElseThrow(()-> new RuntimeException("No account found with id"));
//		Nominee nominee = new Nominee();
//		nominee.setAge(dto.getAge());
//		nominee.setGender(dto.getGender());
//		nominee.setAccountNumber(dto.getAccountNumber());
//		nominee.setName(dto.getName());
//		nominee.setRelation(dto.getRelation());
//		nomineeRepo.save(nominee);
//		account.setNominee(nominee);
//		accRepo.save(account);

		Account fetchedAccount = accRepo.findById(accountId).get();
		Nominee newNominee = fetchedAccount.getNominee();
		newNominee.setName(dto.getName());
		newNominee.setAccountNumber(dto.getAccountNumber());
		newNominee.setRelation(dto.getRelation());
		newNominee.setAge(dto.getAge());
		newNominee.setGender(dto.getGender());
		accRepo.save(fetchedAccount);
	}

	@Override
	public User getKycDetails(Long accountNumber) {
		Account account = accRepo.findByAccountNumber(accountNumber).orElseThrow(()-> new RuntimeException("No account found with id"));
		User user = account.getUser();
		user.setAccountList(null);
		user.setInvestmentList(null);
		return user;
	}

	@Override
	public User updateKyc(KycDto dto, Long accountId) {
		Account account = accRepo.findById(accountId).orElseThrow(()-> new RuntimeException("No account found with id"));
		User user = account.getUser();
		user.setName(dto.getName());
		user.setAddress(dto.getAddress());
		user.setNumber(dto.getNumber());
		user.setIdentityProof(dto.getIdentityProof());
		userRepo.save(user);
		user.setAccountList(null);
		user.setInvestmentList(null);
		return user;
	}

	@Override
	public Account getAccount(Long accountNumber) {
		Account account = accRepo.findByAccountNumber(accountNumber).orElseThrow(()-> new RuntimeException("No account found with id"));
		account.setUser(null);
		return account;
	}

}
