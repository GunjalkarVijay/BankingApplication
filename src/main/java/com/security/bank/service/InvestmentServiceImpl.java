package com.security.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.bank.dto.InvestmentDto;
import com.security.bank.entity.Account;
import com.security.bank.entity.Investment;
import com.security.bank.entity.InvestmentType;
import com.security.bank.entity.User;
import com.security.bank.repository.AccountRepository;
import com.security.bank.repository.InvestmentRepository;
import com.security.bank.repository.UserRepository;

@Service
public class InvestmentServiceImpl implements InvestmentService {

	@Autowired
	AccountRepository accRepo;
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	InvestmentRepository investRepo;

	@Override
	public String invest(Long accountId, InvestmentDto dto) {
//		Account acc = accRepo.findById(accountId).orElseThrow(() -> new RuntimeException("No account found"));
//		if (acc.getBalance() > dto.getAmount()) {
//			throw new RuntimeException("Error in Investment");
//		}
//		Investment invest = new Investment();
//		invest.setAmount(dto.getAmount());
//		invest.setDuration(dto.getDuration());
//		String invType = dto.getInvestmentType();
//		switch (invType) {
//		case "GOLD": {
//			invest.setInvestmentType(InvestmentType.GOLD);
//			break;
//		}
//		case "STOCKS": {
//			invest.setInvestmentType(InvestmentType.STOCKS);
//			break;
//		}
//		case "MUTUAL_FUND": {
//			invest.setInvestmentType(InvestmentType.MUTUAL_FUND);
//			break;
//		}
//		case "FIXED_DEPOSITS": {
//			invest.setInvestmentType(InvestmentType.FIXED_DEPOSITS);
//			break;
//		}
//		default: {
//			throw new RuntimeException("Invalid investment type");
//		}
//		}
//		User user = acc.getUser();
//		invest.setUser(user);
//		investRepo.save(invest);
//		List<Investment> list = user.getInvestmentList();
//		list.add(invest);
//		user.setInvestmentList(list);
//		userRepo.save(user);
//
//		return "Investment successful";

		Account account = accRepo.findById(accountId).get();
		User user =  account.getUser();
		if(account.getBalance()>dto.getAmount()){
			Investment investment = new Investment();
			switch (dto.getInvestmentType()) {
				case "GOLD" -> {
					investment.setInvestmentType(InvestmentType.GOLD);
					investment.setRisk("Low");
					investment.setReturns(12F);
					investment.setCompanyName("BuyNow");
				}
				case "STOCKS" -> {
					investment.setInvestmentType(InvestmentType.STOCKS);
					investment.setRisk("high");
					investment.setReturns(20F);
					investment.setCompanyName("StockWay");
				}
				case "MUTUAL_FUND" -> {
					investment.setInvestmentType(InvestmentType.MUTUAL_FUND);
					investment.setRisk("Moderate");
					investment.setReturns(12.3F);
					investment.setCompanyName("ray fund");
				}
				default -> {
					investment.setInvestmentType(InvestmentType.FIXED_DEPOSITS);
					investment.setRisk("Low");
					investment.setReturns(9.20F);
					investment.setCompanyName("PST");
				}
			}
			investment.setAmount(dto.getAmount());
			investment.setDuration(dto.getDuration());
			investment.setUser(user);
			investRepo.save(investment);
			return "Investment successful";
		}
		throw new RuntimeException("Error in Investment");
	}

}
