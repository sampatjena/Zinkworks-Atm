package com.zinkworks.enterprise.solutions.service;

import com.zinkworks.enterprise.solutions.dto.AccountWithdrawlRequest;
import com.zinkworks.enterprise.solutions.entity.AtmCurrencyInfo;
import com.zinkworks.enterprise.solutions.exception.AccountDetailsException;
import com.zinkworks.enterprise.solutions.exception.AtmBalanceException;

public interface AtmService {
	
public AtmCurrencyInfo getAtmCurrencyDetails(int atmId);
	
	public double getCurrentAtmBalanceAmount(AtmCurrencyInfo paramAtmCurrencyInfo);
	
	public String processCustomerAtmWithdrawalRequest(AccountWithdrawlRequest withdrawlRequest) throws AccountDetailsException , AtmBalanceException ;

}
