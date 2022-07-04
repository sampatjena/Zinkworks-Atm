package com.zinkworks.enterprise.solutions.service;

import com.zinkworks.enterprise.solutions.dto.AccountDetailsRequest;
import com.zinkworks.enterprise.solutions.dto.AccountDetailsResponse;
import com.zinkworks.enterprise.solutions.entity.Account;

public interface AccountDetailsService {
	
	public Account getAccountDetailsFromDbForRequest (AccountDetailsRequest accountDetailsRequest) ;
	public boolean isAccountActive (Account accountDetails ) throws Exception;
	public AccountDetailsResponse getAccountBalanceDetails(AccountDetailsRequest accountDetailsRequest) throws Exception ;
	public void updateAccountDetails(Account accountDetails);
	public Account updateAccountBalance(Account accountDetails , double amountWithdrawn);

}
