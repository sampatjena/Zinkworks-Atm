package com.zinkworks.enterprise.solutions.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.zinkworks.enterprise.solutions.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	Account findByAccountNumberAndAccountPin(String inputAccountNumber , String accountPin);
}
