package com.zinkworks.enterprise.solutions.repository;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.zinkworks.enterprise.solutions.entity.AtmCurrencyInfo;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class AtmCurrencyInfoRepositoryTest {

@Autowired AtmCurrencyInfoRepository atmRepository;
	
	@Test
	public void getAtmDetailsById_Success() throws Exception {
		
		AtmCurrencyInfo testAtmInfo = atmRepository.findAtmCurrencyInfoByAtmIdIs(11);
		assertEquals(testAtmInfo.getAtmId(),11);
		
	}
	
	@Test
	public void getAtmDetailsByAtmId_Failure() throws Exception {
		
		AtmCurrencyInfo testAtmInfo = atmRepository.findAtmCurrencyInfoByAtmIdIs(100);
		Assertions.assertThrows(NullPointerException.class, () -> {
			testAtmInfo.getAtmId();
		});
		}

}
