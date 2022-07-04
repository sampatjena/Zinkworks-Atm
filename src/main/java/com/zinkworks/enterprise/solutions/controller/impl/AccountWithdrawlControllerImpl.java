package com.zinkworks.enterprise.solutions.controller.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zinkworks.enterprise.solutions.controller.AccountWithdrawlController;
import com.zinkworks.enterprise.solutions.dto.AccountWithdrawlRequest;
import com.zinkworks.enterprise.solutions.exception.AccountDetailsException;
import com.zinkworks.enterprise.solutions.exception.AtmBalanceException;
import com.zinkworks.enterprise.solutions.exception.AtmException;
import com.zinkworks.enterprise.solutions.service.AtmService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/api/atm")
public class AccountWithdrawlControllerImpl implements AccountWithdrawlController {
	
	@Autowired AtmService atmService;
	  
	
	@PostMapping(value = "/accountwithdrawl", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Account Withdrawl API", notes = "Atm cash withdrawl Api", response = String.class)
	  @ApiResponses({
	      @ApiResponse(code = 404, message = "Account Number Not Found", response = AccountDetailsException.class),
	      @ApiResponse(code = 400, message = "Atm Exception - Insufficient Atm currency", response = AtmBalanceException.class),
	      @ApiResponse(code = 200, message = "Un Expected Error", response = AtmException.class)
	  })
	public ResponseEntity<String> processCustomerWithdrawlRequest(@Valid @RequestBody AccountWithdrawlRequest withdrawlRequest)
			throws Exception {
		
		return new ResponseEntity<String>(atmService.processCustomerAtmWithdrawalRequest(withdrawlRequest),HttpStatus.OK);

	}

}
