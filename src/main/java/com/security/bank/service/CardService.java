package com.security.bank.service;

import com.security.bank.dto.CardDto;
import com.security.bank.entity.Card;

public interface CardService {

	String block(Long accountNumber, Long cardNumber);

	String applyNew(Long accountNumber, CardDto dto);

	void setting(Card card, Long cardNumber);

}
