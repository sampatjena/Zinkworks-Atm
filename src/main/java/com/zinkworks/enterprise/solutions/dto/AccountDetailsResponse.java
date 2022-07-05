package com.zinkworks.enterprise.solutions.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountDetailsResponse {
	@JsonProperty("accountBalanceAmount")
	private double accountBalanceAmount;
	@JsonProperty("accountOverdraftAmount")	
	private double accountOverdraftAmount;
	
	public AccountDetailsResponse() {}
	
	public double getAccountBalanceAmount() {
		return accountBalanceAmount;
	}
	public void setAccountBalanceAmount(double accountBalanceAmount) {
		this.accountBalanceAmount = accountBalanceAmount;
	}
	public double getAccountOverdraftAmount() {
		return accountOverdraftAmount;
	}
	public void setAccountOverdraftAmount(double accountOverdraftAmount) {
		this.accountOverdraftAmount = accountOverdraftAmount;
	}
	
	public double getAccountTotalWithdrawlAmountWithOverdraft() {
		return getAccountBalanceAmount()+getAccountOverdraftAmount();
	}

}
