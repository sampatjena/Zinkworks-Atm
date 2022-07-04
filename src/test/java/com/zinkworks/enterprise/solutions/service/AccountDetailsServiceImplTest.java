package com.zinkworks.enterprise.solutions.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zinkworks.enterprise.solutions.dto.AccountDetailsRequest;
import com.zinkworks.enterprise.solutions.dto.AccountDetailsResponse;
import com.zinkworks.enterprise.solutions.entity.Account;
import com.zinkworks.enterprise.solutions.repository.AccountRepository;
import com.zinkworks.enterprise.solutions.service.impl.AccountDetailsServiceImpl;
@ExtendWith(MockitoExtension.class)
class AccountDetailsServiceImplTest {

	@Mock
	AccountRepository accountRepository;
	
	@InjectMocks
	AccountDetailsServiceImpl accountService ;
	
	private Account mockAccount;
	private AccountDetailsRequest accountRequest;

	@BeforeEach
	void setUp() throws Exception {
		mockAccount = new Account();
		mockAccount.setAccountNumber("123456789");
		mockAccount.setAccountPin("1234");
		mockAccount.setOpeningBalanceAmount(1000.0);
		mockAccount.setOverdraftAmount(100.0);
		mockAccount.setAccountStatus("Y");
		accountRequest = new AccountDetailsRequest();
		accountRequest.setAccountNumber("123456789");
		accountRequest.setAccountPin("1234");
	}
	
	

	@Test
	void testGetAccountDetailsFromDbForRequest() {
		when(accountRepository.findByAccountNumberAndAccountPin("123456789","1234")).thenReturn(mockAccount);
		Account testAccount = accountService.getAccountDetailsFromDbForRequest(accountRequest);
		assertThat(testAccount).isNotNull();
	}

	@Test
	void testIsAccountActive() throws Exception{
		boolean accountStatus = accountService.isAccountActive(mockAccount);
		assertEquals(accountStatus,true);
	}

	@Test
	void testGetAccountBalanceDetails() throws Exception {
		when(accountService.getAccountDetailsFromDbForRequest(accountRequest)).thenReturn(mockAccount);
		AccountDetailsResponse accountBalanceResponse = accountService.getAccountBalanceDetails(accountRequest);
		assertThat(accountBalanceResponse).isNotNull();
		assertEquals(1000.0, accountBalanceResponse.getAccountBalanceAmount());
	}


	@Test
	void testUpdateAccountBalance() {
		Account mockUpdatableAccount = new Account();
		mockUpdatableAccount.setAccountNumber("987654321");
		mockUpdatableAccount.setAccountPin("9080");
		mockUpdatableAccount.setAccountStatus("Y");
		mockUpdatableAccount.setOpeningBalanceAmount(80);
		mockUpdatableAccount.setOverdraftAmount(50);
		
		mockUpdatableAccount = accountService.updateAccountBalance(mockUpdatableAccount, 35);
		assertEquals(mockUpdatableAccount.getOpeningBalanceAmount(),45.0);
	}

}
