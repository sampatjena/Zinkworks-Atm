package com.zinkworks.enterprise.solutions.controller;

import org.springframework.http.ResponseEntity;

import com.zinkworks.enterprise.solutions.dto.AccountWithdrawlRequest;

public interface AccountWithdrawlController {
	public ResponseEntity<String> processCustomerWithdrawlRequest(AccountWithdrawlRequest withdrawlRequest) throws Exception;

}
