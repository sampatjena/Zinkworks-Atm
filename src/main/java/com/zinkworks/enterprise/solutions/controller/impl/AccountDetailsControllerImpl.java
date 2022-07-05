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

import com.zinkworks.enterprise.solutions.controller.AccountDetailsController;
import com.zinkworks.enterprise.solutions.dto.AccountDetailsRequest;
import com.zinkworks.enterprise.solutions.dto.AccountDetailsResponse;
import com.zinkworks.enterprise.solutions.exception.AccountDetailsException;
import com.zinkworks.enterprise.solutions.exception.AtmBalanceException;
import com.zinkworks.enterprise.solutions.exception.AtmException;
import com.zinkworks.enterprise.solutions.service.AccountDetailsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "AccountDetails", description = "the AccountDetails API")
@RequestMapping("/v1/api/atm")
public class AccountDetailsControllerImpl implements AccountDetailsController {
	
	@Autowired AccountDetailsService accountServiceObject;
	 @ApiOperation(value = "Account details API", notes = "Account details balance api", response = AccountDetailsResponse.class)
	  @ApiResponses ({
		  @ApiResponse(code =200, message =" Account detail", response = AccountDetailsResponse.class),
	      @ApiResponse(code = 404, message = "Account Number Not Found", response = AccountDetailsException.class),
	      @ApiResponse(code = 500, message = "Un Expected Error", response = AtmException.class)
	  })

	@PostMapping(value = "/accountdetails" ,produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<AccountDetailsResponse> displayAccountDetailsForCustomer(@Valid @RequestBody AccountDetailsRequest accountDetailsRequest)  throws Exception{
		AccountDetailsResponse accountBalanceResponse = accountServiceObject.getAccountBalanceDetails(accountDetailsRequest);
		return new ResponseEntity<AccountDetailsResponse>(accountBalanceResponse,HttpStatus.OK);
	}

}
