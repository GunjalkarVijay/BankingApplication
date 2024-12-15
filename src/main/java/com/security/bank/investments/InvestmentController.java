package com.security.bank.investments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.security.bank.dto.InvestmentDto;
import com.security.bank.service.InvestmentService;

@RestController
@RequestMapping("/invest")
public class InvestmentController {

	@Autowired
	InvestmentService service;
	
//	@ResponseStatus(HttpStatus.CREATED)
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/now")
	public String invest(@RequestParam Long accountId, @RequestBody InvestmentDto dto) {
		return service.invest(accountId,dto);
	}
}
