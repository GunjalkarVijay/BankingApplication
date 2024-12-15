package com.security.bank.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.bank.dto.CardDto;
import com.security.bank.entity.Account;
import com.security.bank.entity.Card;
import com.security.bank.entity.CardType;
import com.security.bank.repository.AccountRepository;
import com.security.bank.repository.CardRepository;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	AccountRepository accRepo;

	@Autowired
	CardRepository cardRepo;

	public String randomGenerator(int size) {
		String ans ="";
		for(int i=0; i<size; i++) {
			ans+= (Math.random()*10);
		}
		return ans;
	}

	@Override
	public String block(Long accountNumber, Long cardNumber) {
//		Account acc = accRepo.findByAccountNumber(accountNumber).orElseThrow(()-> new RuntimeException("Account not found"));
//		if(acc.getCard().getCardNumber() != cardNumber) {
//			throw new RuntimeException("No Card Found with the given cardNumber: "+ cardNumber);
//		}
//		Card card = acc.getCard();
//		card.setStatus("INACTIVE");
//		acc.setCard(card);
//		cardRepo.save(card);
//		accRepo.save(acc);
//		return "Card Blocked Successfully";

		Account account = accRepo.findByAccountNumber(accountNumber).get();
		Card card = cardRepo.findByCardNumber(cardNumber).get();
		if(account.getCard().getCardNumber().equals(card.getCardNumber())){
			account.setCard(null);
			accRepo.save(account);
			cardRepo.deleteById(card.getId());
			return "Card Blocked Successfully";
		}
		throw new RuntimeException("No Card Found with the given cardNumber: "+ cardNumber);
	}

	@Override
	public String applyNew(Long accountNumber, CardDto dto) {
//		Account acc = accRepo.findByAccountNumber(accountNumber).orElseThrow(()-> new RuntimeException("Account not found"));
//		if(acc.getCard()!=null && acc.getCard().getStatus().equalsIgnoreCase("ACTIVE")) {
//			throw new RuntimeException("Account with number: "+ accountNumber+" already has a card.");
//		}
//
//		Card card = new Card();
//		card.setCardHolderName(dto.getCardHolderName());
//		card.setAllocationDate(new Date());
//		card.setCardNumber(Long.parseLong(randomGenerator(12)));
//		card.setCvv(Integer.parseInt(randomGenerator(3)));
//
//		//setting expiry date
//		Calendar calender = Calendar.getInstance();
//		calender.setTime(new Date());
//		calender.add(Calendar.YEAR, 5);
//		card.setExpiryDate(calender.getTime());
//
//		card.setPin(dto.getPin());
//		card.setStatus("ACTIVE");
//
//		String cardType = dto.getCardType();
//		switch(cardType) {
//		case "DEBIT_CLASSIC":{
//			card.setCardType(CardType.DEBIT_CLASSIC);
//			card.setDailyLimit(20000);
//			break;
//		}
//		case "DEBIT_GLOBAL":{
//			card.setCardType(CardType.DEBIT_GLOBAL);
//			card.setDailyLimit(40000);
//			break;
//		}
//		case "CREDIT_PREMIUM":{
//			card.setCardType(CardType.CREDIT_PREMIUM);
//			card.setDailyLimit(50000);
//			break;
//		}
//		case "CREDIT_MASTER":{
//			card.setCardType(CardType.CREDIT_MASTER);
//			card.setDailyLimit(75000);
//			break;
//		}
//		default :{
//			throw new RuntimeException("Invalid card type");
//		}
//		}
//		cardRepo.save(card);
//		acc.setCard(card);
//		accRepo.save(acc);

		Account account = accRepo.findByAccountNumber(accountNumber).get();
//		if(account.getCard() != null && account.getCard().getCardNumber() != null){
//			throw new RuntimeException("Account with number: "+ accountNumber+" already has a card.");
//		}
		Card card = new Card();
		card.setCardNumber(Long.parseLong(generateCardNumber()));
		card.setCvv(generateCvv());
		switch (dto.getCardType()) {
			case "DEBIT_CLASSIC" -> {
				card.setDailyLimit(20000);
				card.setCardType(CardType.DEBIT_CLASSIC);
			}
			case "CREDIT_PREMIUM" -> {
				card.setDailyLimit(50000);
				card.setCardType(CardType.CREDIT_PREMIUM);
			}
			case "CREDIT_MASTER" -> {
				card.setDailyLimit(75000);
				card.setCardType(CardType.CREDIT_MASTER);
			}
			default -> {
				card.setDailyLimit(40000);
				card.setCardType(CardType.DEBIT_GLOBAL);
			}
		}
		card.setPin(dto.getPin());
		card.setAllocationDate(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, 5);
		card.setExpiryDate(calendar.getTime());
		card.setCardHolderName(dto.getCardHolderName());
		card.setStatus("ACTIVE");
		Card savedCard = cardRepo.save(card);
		account.setCard(savedCard);
		accRepo.save(account);
		return "New Card Allocated to account wih Number: "+ accountNumber;

	}

	public int generateCvv() {
		Random random = new Random();
		// Generating a random 3-digit number for CVV
		return random.nextInt(900) + 100;
	}

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

	@Override
	public void setting(Card card, Long cardNumber) {
		Card cardOri = cardRepo.findByCardNumber(cardNumber).orElseThrow(()-> new RuntimeException("No Card found with given card number"));
		cardOri.setPin(card.getPin());
		CardType cardType = cardOri.getCardType();
		String type = cardType.toString();
		double dailyLimit = card.getDailyLimit();
		switch(type) {
		case "DEBIT_CLASSIC":{
			if(dailyLimit<=40000) {
				cardOri.setDailyLimit(dailyLimit);
			}else {
				throw new RuntimeException("Limit exceeded");
			}
			break;
		}
		case "DEBIT_GLOBAL":{
			if(dailyLimit<=50000) {
				cardOri.setDailyLimit(dailyLimit);
			}else {
				throw new RuntimeException("Limit exceeded");
			}
			break;
		}
		case "CREDIT_PREMIUM":{
			if(dailyLimit<=75000) {
				cardOri.setDailyLimit(dailyLimit);
			}else {
				throw new RuntimeException("Limit exceeded");
			}
			break;
		}
		case "CREDIT_MASTER":{
			if(dailyLimit<=100000) {
				cardOri.setDailyLimit(dailyLimit);
			}else {
				throw new RuntimeException("Limit exceeded");
			}
			break;
		}
		default :{
			throw new RuntimeException("Invalid card type");
		}
		}
		cardRepo.save(cardOri);
	}

}
