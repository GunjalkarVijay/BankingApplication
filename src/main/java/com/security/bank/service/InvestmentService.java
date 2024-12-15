package com.security.bank.service;

import com.security.bank.dto.InvestmentDto;

public interface InvestmentService {

	String invest(Long accountId, InvestmentDto dto);

}
