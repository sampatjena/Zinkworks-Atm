package com.zinkworks.enterprise.solutions.service.impl;

import java.sql.SQLException;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zinkworks.enterprise.solutions.dto.AccountWithdrawlRequest;
import com.zinkworks.enterprise.solutions.dto.AtmCurrency;
import com.zinkworks.enterprise.solutions.entity.Account;
import com.zinkworks.enterprise.solutions.entity.AtmCurrencyInfo;
import com.zinkworks.enterprise.solutions.exception.AccountDetailsException;
import com.zinkworks.enterprise.solutions.exception.AtmBalanceException;
import com.zinkworks.enterprise.solutions.exception.AtmException;
import com.zinkworks.enterprise.solutions.repository.AtmCurrencyInfoRepository;
import com.zinkworks.enterprise.solutions.service.AccountDetailsService;
import com.zinkworks.enterprise.solutions.service.AtmService;

@Service
public class AtmServiceImpl implements AtmService {
	
	@Value("${account.valid.message}")
	private String accountErrorMessage;
	
	@Value("${account.balance.message}")
	private String accountBalanceErrorMessage;
	
	@Value("${incorrectatmamount.message}")
	private String atmAmountNotValid;
	
	@Value("${atmcurrency.message}")
	private String atmErrorMessage;
	
	@Value("${atmnocurrency.message}")
	private String atmCurrencyMessage;
	
	@Autowired
	private AtmCurrencyInfoRepository atmCurrencyInfoRepository;
	@Autowired
	private AccountDetailsService accountService;
	
	
	@Override
	public AtmCurrencyInfo getAtmCurrencyDetails(int atmId) {
		return atmCurrencyInfoRepository.findAtmCurrencyInfoByAtmIdIs(atmId);
		
	}
	
	public double getCurrentAtmBalanceAmount(AtmCurrencyInfo paramAtmCurrencyInfo) {
		double currentAtmBalanceAmount = 0.0;
		Optional<AtmCurrencyInfo> atmCurrencyDetailsObj = Optional.ofNullable(paramAtmCurrencyInfo);
		if (atmCurrencyDetailsObj.isPresent()) {
			double fiftyEuroCurrencyCount = atmCurrencyDetailsObj.get().getFiftyEuroCurrencyCount();
			double twentyEuroCurrencyCount = atmCurrencyDetailsObj.get().getTwentyEuroCurrencyCount();
			double tenEuroCurrencyCount = atmCurrencyDetailsObj.get().getTenEuroCurrencyCount();
			double fiveEuroCurrencyCount = atmCurrencyDetailsObj.get().getFiveEuroCurrencyCount();
			
			currentAtmBalanceAmount = (fiftyEuroCurrencyCount *50)
									+ (twentyEuroCurrencyCount *20)
									+ (tenEuroCurrencyCount * 10)
									+ (fiveEuroCurrencyCount * 5);
		}

		return currentAtmBalanceAmount;
		
	}
	
	
	public AtmCurrency getRequestedWithdrawlAmountCurrencyDenominationCounts(int requestedWithdrawlAmount , AtmCurrencyInfo atmCurrencyInfo ) {
		AtmCurrency disbursedCurrencyDetails = new AtmCurrency() ;
		 int fiftyCurrencyCount=0, twentyCurrencyCount=0, tenCurrencyCount=0, fiveCurrencyCount=0;
		 while (requestedWithdrawlAmount >= 50 ) {
			 fiftyCurrencyCount = requestedWithdrawlAmount / 50 ;
			 requestedWithdrawlAmount = requestedWithdrawlAmount % 50; 
			 break;
		 }
		 while (requestedWithdrawlAmount >= 20) {
			 twentyCurrencyCount = requestedWithdrawlAmount / 20 ;
			 requestedWithdrawlAmount = requestedWithdrawlAmount % 20; 
			 break;
		 }
		 while (requestedWithdrawlAmount >= 10) {
			 tenCurrencyCount = requestedWithdrawlAmount / 10 ;
			 requestedWithdrawlAmount = requestedWithdrawlAmount % 10; 
			 break;
		 }
		 while (requestedWithdrawlAmount >= 5) {
			 fiveCurrencyCount = requestedWithdrawlAmount / 5 ;
			 requestedWithdrawlAmount = requestedWithdrawlAmount % 5; 
			 break;
		 }
		 
		 disbursedCurrencyDetails.setFiftyCurrencyCount(fiftyCurrencyCount);
		 disbursedCurrencyDetails.setTwentyCurrencyCount(twentyCurrencyCount);
		 disbursedCurrencyDetails.setTenCurrencyCount(tenCurrencyCount);
		 disbursedCurrencyDetails.setFiveCurrencyCount(fiveCurrencyCount);
		 disbursedCurrencyDetails.setAmountNotDisburshed(requestedWithdrawlAmount);
		 
		 return disbursedCurrencyDetails;
		 
	}
	
	public String processCustomerAtmWithdrawalRequest(AccountWithdrawlRequest withdrawlRequest) throws AccountDetailsException , AtmBalanceException , AtmException {
		StringBuilder withdrawlRequestStatus = new StringBuilder();
		AtmCurrency disbursedCurrencyDetails = new AtmCurrency();
		 Optional<Account> accountObject = Optional.ofNullable(accountService.getAccountDetailsFromDbForRequest(withdrawlRequest)); 
		 if (accountObject.isPresent()) {  // Account valid
			 Account verifiedAccountObject = accountObject.get();
			 double customerAccountBalanceWithOverdraft = verifiedAccountObject.getOpeningBalanceAmount()
					 		+ verifiedAccountObject.getOverdraftAmount();
			 Optional<AtmCurrencyInfo> atmCurrencyInfo = Optional.ofNullable(getAtmCurrencyDetails(withdrawlRequest.getAtmId()));
			 if (atmCurrencyInfo.isPresent()) {  // Atm Currency available
				  double atmCurrencyBalance = getCurrentAtmBalanceAmount(atmCurrencyInfo.get()) ;
				  
				  if ( withdrawlRequest.getWithdrawlAmount() <= atmCurrencyBalance ) { // AtmCurrency less than equal to withdrawl amount
					 if (withdrawlRequest.getWithdrawlAmount() <= customerAccountBalanceWithOverdraft ) {
						 disbursedCurrencyDetails = getRequestedWithdrawlAmountCurrencyDenominationCounts(withdrawlRequest.getWithdrawlAmount() , atmCurrencyInfo.get() );
						 if (disbursedCurrencyDetails.getAmountNotDisburshed() == 0) {  // Requested amount not disburses
							 withdrawlRequestStatus.append(disbursedCurrencyDetails.getDisdursedCurrencyDetailsAsString());
							 accountService.updateAccountBalance(verifiedAccountObject, withdrawlRequest.getWithdrawlAmount());
							 updateAccountAndAtmCurrency(atmCurrencyInfo.get() ,disbursedCurrencyDetails , verifiedAccountObject);
							 
							 }
							 else {
								 throw new AtmBalanceException (atmAmountNotValid);
							 }
						 }
						 else 
						 {
							 throw new AccountDetailsException(accountBalanceErrorMessage);
						 }
				 }
				 else {
					 throw new  AtmException(atmCurrencyMessage);
				 }
			 }
			 else {
				 throw new AtmException(atmAmountNotValid);
			 }
		 } else {
			 throw new AccountDetailsException(accountErrorMessage);
		 }
		 
		 return withdrawlRequestStatus.toString();
			 
	}
	
	@Transactional(rollbackFor = SQLException.class)
	public void updateAccountAndAtmCurrency(AtmCurrencyInfo currentAtmCurrencyInfo , 
				AtmCurrency disbursedAtmCurrency , Account accountObject)
	{
		currentAtmCurrencyInfo.setFiftyEuroCurrencyCount(currentAtmCurrencyInfo.getFiftyEuroCurrencyCount()-disbursedAtmCurrency.getFiftyCurrencyCount());
		currentAtmCurrencyInfo.setTwentyEuroCurrencyCount(currentAtmCurrencyInfo.getTwentyEuroCurrencyCount()-disbursedAtmCurrency.getTwentyCurrencyCount());
		currentAtmCurrencyInfo.setTenEuroCurrencyCount(currentAtmCurrencyInfo.getTenEuroCurrencyCount()-disbursedAtmCurrency.getTenCurrencyCount());
		currentAtmCurrencyInfo.setFiveEuroCurrencyCount(currentAtmCurrencyInfo.getFiveEuroCurrencyCount()-disbursedAtmCurrency.getFiveCurrencyCount());
		accountService.updateAccountDetails(accountObject);
		atmCurrencyInfoRepository.saveAndFlush(currentAtmCurrencyInfo);
	}

}
