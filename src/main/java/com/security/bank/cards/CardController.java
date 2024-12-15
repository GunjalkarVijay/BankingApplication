package com.security.bank.cards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.security.bank.dto.CardDto;
import com.security.bank.entity.Card;
import com.security.bank.service.CardService;

@RestController
@RequestMapping("/card")
public class CardController {
	
	@Autowired
	CardService service;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/block")
	public String block(@RequestParam Long accountNumber, @RequestParam Long cardNumber) {
		return service.block(accountNumber,cardNumber);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/apply/new")
	public String applyNew(@RequestParam Long accountNumber, @RequestBody CardDto dto) {
		return service.applyNew(accountNumber,dto);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/setting")
	public void setting(@RequestBody Card card, @RequestParam Long cardNumber) {
		service.setting(card,cardNumber);
	}

}
