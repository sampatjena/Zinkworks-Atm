package com.zinkworks.enterprise.solutions.service.impl;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zinkworks.enterprise.solutions.dto.AccountDetailsRequest;
import com.zinkworks.enterprise.solutions.dto.AccountDetailsResponse;
import com.zinkworks.enterprise.solutions.dto.AccountWithdrawlResponse;
import com.zinkworks.enterprise.solutions.entity.Account;
import com.zinkworks.enterprise.solutions.exception.AccountDetailsException;
import com.zinkworks.enterprise.solutions.repository.AccountRepository;
import com.zinkworks.enterprise.solutions.service.AccountDetailsService;

@Service
public class AccountDetailsServiceImpl implements AccountDetailsService{
	
	@Value("${account.valid.message}")
	private String accountErrorMessage;

	@Autowired
	public AccountRepository accountRepositoryObject;
	

	@Override
	public Account getAccountDetailsFromDbForRequest(AccountDetailsRequest accountDetailsRequest) {
		return accountRepositoryObject.findByAccountNumberAndAccountPin(accountDetailsRequest.getAccountNumber() , accountDetailsRequest.getAccountPin());
	}

	@Override
	public boolean isAccountActive(Account selectedAccount)  {
		boolean accountActive = false;
		if (selectedAccount != null) {
			if (selectedAccount.getAccountStatus().equalsIgnoreCase("Y"))
				accountActive = true;
		}
		return accountActive;
	}
	
	
	public AccountDetailsResponse getAccountBalanceDetails(AccountDetailsRequest accountDetailsRequest) throws AccountDetailsException {
		AccountDetailsResponse accountBalanceResponseObject = null;
		
			Optional<Account> accountDetailsFromDB = Optional.ofNullable(getAccountDetailsFromDbForRequest(accountDetailsRequest));
				if (accountDetailsFromDB.isPresent() && isAccountActive(accountDetailsFromDB.get())) {
						
						accountBalanceResponseObject = new AccountDetailsResponse();
						accountBalanceResponseObject.setAccountBalanceAmount(accountDetailsFromDB.get().getOpeningBalanceAmount());
						accountBalanceResponseObject.setAccountOverdraftAmount(accountDetailsFromDB.get().getOverdraftAmount());
						
					}
				else {
					throw new AccountDetailsException(accountErrorMessage);
				}
			
		return accountBalanceResponseObject;
	}
	
	public void updateAccountDetails(Account accountDetails) {
		accountRepositoryObject.save(accountDetails);
	}
	
	public Account updateAccountBalance(Account accountDetails , double amountWithdrawn) {
		double currentOpeningBalance = accountDetails.getOpeningBalanceAmount();
		double currentOverdraftAmount = accountDetails.getOverdraftAmount();
		if ((currentOpeningBalance != 0.0) && 
				((currentOpeningBalance - amountWithdrawn) > 0))
		{
			accountDetails.setOpeningBalanceAmount(currentOpeningBalance - amountWithdrawn);
		}
		
		else if ((currentOpeningBalance == 0.0) &&
				(currentOverdraftAmount - amountWithdrawn) > 0)
		{
			accountDetails.setOverdraftAmount(amountWithdrawn);
		}
		if (currentOpeningBalance - amountWithdrawn == 0) {
			accountDetails.setOpeningBalanceAmount(0);
		}
		if ((accountDetails.getOpeningBalanceAmount()== 0) 
				&& (currentOverdraftAmount - amountWithdrawn == 0)) {
			accountDetails.setOverdraftAmount(0);
		}
		return accountDetails;
	}
	
	public AccountWithdrawlResponse updateResponseWithAccountBalance( AccountWithdrawlResponse withdrawlResponse) {
		AccountWithdrawlResponse updatedWithDrawlResponse = new AccountWithdrawlResponse();
		updatedWithDrawlResponse.setFiftyCurrencyCount(withdrawlResponse.getFiftyCurrencyCount());
		updatedWithDrawlResponse.setTwentyCurrencyCount(withdrawlResponse.getTwentyCurrencyCount());
		updatedWithDrawlResponse.setTenCurrencyCount(withdrawlResponse.getTenCurrencyCount());
		updatedWithDrawlResponse.setFiveCurrencyCount(withdrawlResponse.getFiveCurrencyCount());
		/*
		 * AccountDetailsRequest updatedDetailsRequest = new
		 * AccountDetailsRequest();
		 * updatedDetailsRequest.setAccountNumber(accountDetails.
		 * getAccountNumber());
		 * updatedDetailsRequest.setAccountPin(accountDetails.getAccountPin());
		 * Account updatedAccountDetails =
		 * getAccountDetailsFromDbForRequest(updatedDetailsRequest);
		 * updatedWithDrawlResponse.setNewWithdrawlBalance(updatedAccountDetails
		 * .getOpeningBalanceAmount());
		 * updatedWithDrawlResponse.setNewOverdraftBalance(updatedAccountDetails
		 * .getOverdraftAmount());
		 */
		return updatedWithDrawlResponse;
	}

}
