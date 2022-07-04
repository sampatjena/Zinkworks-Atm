package com.zinkworks.enterprise.solutions.controller;

import org.springframework.http.ResponseEntity;

import com.zinkworks.enterprise.solutions.dto.AccountDetailsRequest;
import com.zinkworks.enterprise.solutions.dto.AccountDetailsResponse;

public interface AccountDetailsController {
	
	public ResponseEntity<AccountDetailsResponse> displayAccountDetailsForCustomer(AccountDetailsRequest accountDetailsRequest) throws Exception;

}
