package com.zinkworks.enterprise.solutions.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.zinkworks.enterprise.solutions.entity.Account;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class AccountRepositoryTest {
	
	@Autowired
	AccountRepository accountRepository;

	@Test
	public void getAllAccounts()
	{
		Account testAccount = accountRepository.findByAccountNumberAndAccountPin("123456789", "1234");
		assertEquals(testAccount.getAccountNumber(), "123456789");
	}
	
	@Test
	public void getAccountForInvalidPinThrowsException ()
	{
		Account testAccount = accountRepository.findByAccountNumberAndAccountPin("123456789", "1239");
		Assertions.assertThrows(NullPointerException.class, () -> {
			testAccount.getAccountNumber();
			
		});
	}

}
