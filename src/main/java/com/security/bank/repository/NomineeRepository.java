package com.security.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.bank.entity.Nominee;

@Repository
public interface NomineeRepository extends JpaRepository<Nominee, Long>{

}
