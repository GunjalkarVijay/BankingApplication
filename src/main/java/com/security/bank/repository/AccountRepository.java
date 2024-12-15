package com.security.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.security.bank.entity.Account;
import com.security.bank.entity.AccountType;
import com.security.bank.entity.BranchType;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	public Optional<Account> findByAccountNumber(Long accountNumber);
	
	@Query("SELECT ac FROM Account ac WHERE LOWER(ac.status) = 'active'")
	List<Account> findAllActiveAccounts();
	
	@Query("SELECT ac FROM Account ac WHERE LOWER(ac.status) = 'inactive'")
	List<Account> findAllInActiveAccounts();
	
	@Query("SELECT ac FROM Account ac WHERE ac.accountType = ?1")
	List<Account> findAllByAccountType(AccountType accountType);
	
//	@Query("SELECT ac FROM Account ac WHERE ac.branchType = ?1")
	@Query("SELECT ac FROM Account ac WHERE ac.branch = ?1")
	List<Account> findAllByBranchType(BranchType branchType);
}
