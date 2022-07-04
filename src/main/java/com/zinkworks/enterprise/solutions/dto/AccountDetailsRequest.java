package com.zinkworks.enterprise.solutions.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class AccountDetailsRequest {
	
	@NotBlank
	@Size(min=9, max=9 , message="Account number must be 9 numbers" )
	@Positive(message = "Enter a valid account number")
	private String accountNumber;
	
	@NotBlank
	@Size(min=4 , max =4 , message="Pin must be 4 numbers")
	@Positive(message = "Enter a valid account pin")
	private String accountPin;
	
	public AccountDetailsRequest() {
		super();
		}
	
	public AccountDetailsRequest(String accountNumber, String accountPin) {
		this.accountNumber = accountNumber;
		this.accountPin = accountPin;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountPin() {
		return accountPin;
	}
	public void setAccountPin(String accountPin) {
		this.accountPin = accountPin;
	}

}
