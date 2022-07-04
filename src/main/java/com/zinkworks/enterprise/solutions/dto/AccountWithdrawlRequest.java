package com.zinkworks.enterprise.solutions.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AccountWithdrawlRequest extends AccountDetailsRequest {
	
	@NotNull(message = "Withdrawl amount is required")
	@Positive(message = "Please proivide a valid amount")
	private int withdrawlAmount;
	
	@NotNull(message = "Atm is is required")
	@Positive(message = "Atm id is not valid")
	private int atmId ;

	public AccountWithdrawlRequest() {
		super();
	}

	public AccountWithdrawlRequest(String accountNumber, String accountPin,
			int withdrawlAmount , int atmId ) {
		super(accountNumber, accountPin);
		this.withdrawlAmount = withdrawlAmount;
		this.atmId = atmId;
		
	}

	public int getWithdrawlAmount() {
		return withdrawlAmount;
	}

	public void setWithdrawlAmount(int withdrawlAmount) {
		this.withdrawlAmount = withdrawlAmount;
	}

	public int getAtmId() {
		return atmId;
	}

	public void setAtmId(int atmId) {
		this.atmId = atmId;
	}
	
	
	
	

}
