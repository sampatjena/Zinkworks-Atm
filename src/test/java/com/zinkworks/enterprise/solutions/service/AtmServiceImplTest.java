package com.zinkworks.enterprise.solutions.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zinkworks.enterprise.solutions.dto.AccountWithdrawlRequest;
import com.zinkworks.enterprise.solutions.dto.AtmCurrency;
import com.zinkworks.enterprise.solutions.entity.Account;
import com.zinkworks.enterprise.solutions.entity.AtmCurrencyInfo;
import com.zinkworks.enterprise.solutions.repository.AccountRepository;
import com.zinkworks.enterprise.solutions.repository.AtmCurrencyInfoRepository;
import com.zinkworks.enterprise.solutions.service.impl.AccountDetailsServiceImpl;
import com.zinkworks.enterprise.solutions.service.impl.AtmServiceImpl;
@ExtendWith(MockitoExtension.class)
class AtmServiceImplTest {

	@InjectMocks
	private AtmServiceImpl atmService ;
	@InjectMocks
	private AccountDetailsServiceImpl accountService = Mockito.mock(AccountDetailsServiceImpl.class);
	
	@Mock
	AtmCurrencyInfoRepository atmCurrencyInfoRepository ;
	@Mock
	AccountRepository accountRepository;
	
	private AtmCurrencyInfo atmInfo;
	private AtmCurrencyInfo mockAtmInfo;
	private List<AtmCurrencyInfo> atmInfoList;
	private List<Account> accountList;
	private AtmCurrency atmCurrency;
	private AccountWithdrawlRequest withdrawlRequest;
	private Account account;
	
	@BeforeEach
    public void setup(){
		
		atmInfoList = new ArrayList<>(2);
		atmInfo = new AtmCurrencyInfo();
        atmInfo.setAtmId(20);
        atmInfo.setFiftyEuroCurrencyCount(10);
        atmInfo.setTwentyEuroCurrencyCount(6);
        atmInfo.setTenEuroCurrencyCount(8);
        atmInfo.setFiveEuroCurrencyCount(4);
        mockAtmInfo = new AtmCurrencyInfo();
        mockAtmInfo.setAtmId(20);
        mockAtmInfo.setFiftyEuroCurrencyCount(10);
        mockAtmInfo.setTwentyEuroCurrencyCount(6);
        mockAtmInfo.setTenEuroCurrencyCount(8);
        mockAtmInfo.setFiveEuroCurrencyCount(4);
        atmInfoList.add(atmInfo);
        atmInfoList.add(mockAtmInfo);
        atmCurrency = new AtmCurrency();
		atmCurrency.setFiftyCurrencyCount(1);
		atmCurrency.setTwentyCurrencyCount(1);
		atmCurrency.setTenCurrencyCount(2);
		atmCurrency.setFiveCurrencyCount(2);
		atmCurrency.setAmountNotDisburshed(0);
		withdrawlRequest = new AccountWithdrawlRequest();
		withdrawlRequest.setAccountNumber("123456789");
		withdrawlRequest.setAccountPin("1234");
		withdrawlRequest.setAtmId(20);
		withdrawlRequest.setWithdrawlAmount(100);
		accountList = new ArrayList<>(1);
		account = new Account();
		account.setAccountNumber("123456789");
		account.setAccountPin("1234");
		account.setOpeningBalanceAmount(300.0);
		account.setOverdraftAmount(100.0);
		accountList.add(account);
        
    }
	

	@Test
	public void test_getAtmCurrencyDetails()
	{
		AtmCurrencyInfo atmInfoObj = new AtmCurrencyInfo();
		atmInfoObj.setAtmId(21);
		atmInfoObj.setFiftyEuroCurrencyCount(10);
		atmInfoObj.setTwentyEuroCurrencyCount(6);
		atmInfoObj.setTenEuroCurrencyCount(8);
		atmInfoObj.setFiveEuroCurrencyCount(4);
		
		when(atmCurrencyInfoRepository.findAtmCurrencyInfoByAtmIdIs(20)).thenReturn(atmInfoList.get(1));
		assertThat(atmInfoList).isNotNull();
		AtmCurrencyInfo atmCurrencyInfo = atmService.getAtmCurrencyDetails(20);
		
		assertThat(atmCurrencyInfo).isNotNull();
		
	}
	
	@Test
	public void test_getCurrentAtmBalanceAmount() {
		
		assertThat(atmInfoList).isNotNull();
		double currentAtmBalance = atmService.getCurrentAtmBalanceAmount(mockAtmInfo);
		assertThat(currentAtmBalance).isEqualTo(new Double(720).doubleValue());
	}
	
	@Test
	public void test_getRequestedWithdrawlAmountCurrencyDenominationCounts() {
		
		
		assertThat(atmInfoList).isNotNull();
		AtmCurrencyInfo atmCurrencyInfo = atmService.getAtmCurrencyDetails(20);
		AtmCurrency mockAtmCurrency = atmService.getRequestedWithdrawlAmountCurrencyDenominationCounts(100, atmCurrencyInfo);
		assertThat(mockAtmCurrency.getFiftyCurrencyCount()).isEqualTo(new Integer(2).intValue());
		assertThat(mockAtmCurrency.getAmountNotDisburshed()).isEqualTo(new Integer(0).intValue());
		
	}
	
	@Test
	public void test_processCustomerAtmWithdrawalRequest() {
		
	//	when(accountRepository.findByAccountNumberAndAccountPin("123456789", "1234")).thenReturn(accountList.get(0));
		assertThat(accountList).isNotEmpty();
		when(atmCurrencyInfoRepository.findAtmCurrencyInfoByAtmIdIs(20)).thenReturn(atmInfoList.get(1));
		AtmCurrencyInfo atmCurrencyInfo = atmService.getAtmCurrencyDetails(20);
		assertThat(atmCurrencyInfo).isNotNull();
		when(accountService.getAccountDetailsFromDbForRequest(withdrawlRequest)).thenReturn(accountList.get(0));
		
		String disbursedCurrencyDetails = atmService.processCustomerAtmWithdrawalRequest(withdrawlRequest);
		assertThat(disbursedCurrencyDetails).isNotBlank();
		
	}

}
