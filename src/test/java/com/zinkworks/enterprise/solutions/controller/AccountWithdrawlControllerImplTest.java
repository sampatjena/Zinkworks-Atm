package com.zinkworks.enterprise.solutions.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.zinkworks.enterprise.solutions.dto.AccountDetailsResponse;
import com.zinkworks.enterprise.solutions.dto.AccountWithdrawlRequest;
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class AccountWithdrawlControllerImplTest {

	@LocalServerPort
	 int randomServerPort;
	 
	 final RestTemplate restTemplate = new RestTemplate();
	
	@Test
	public void testAtmWithdrawlForAccountWithSuccess() throws Exception {
		AccountWithdrawlRequest withdrawlRequest = new AccountWithdrawlRequest();
		withdrawlRequest.setAccountNumber("987654321");
		withdrawlRequest.setAccountPin("4321");
		withdrawlRequest.setWithdrawlAmount(80);
		withdrawlRequest.setAtmId(11);
		
		
		final String accountWithdrawlControllerUrl = "http://localhost:"+randomServerPort+"/v1/api/atm/accountwithdrawl";
		URI uri = new URI(accountWithdrawlControllerUrl);
		
       HttpHeaders headers = new HttpHeaders();
       headers.set("X-COM-PERSIST", "true"); 
		
       HttpEntity<AccountWithdrawlRequest> request = new HttpEntity(withdrawlRequest, headers);
       ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
               
       Assertions.assertEquals(200, result.getStatusCodeValue());
       Assertions.assertEquals(true, result.getBody().contains("Currency disbursed"));
    
			      

	}
	
	@Test
	public void testAtmWithdrawlForAccountWithFailure() throws Exception {
		AccountWithdrawlRequest withdrawlRequest = new AccountWithdrawlRequest();
		withdrawlRequest.setAccountNumber("987654321");
		withdrawlRequest.setAccountPin("4321");
		withdrawlRequest.setWithdrawlAmount(6);
		withdrawlRequest.setAtmId(11);
		
		RestTemplate restTemplate = new RestTemplate();
		final String accountWithdrawlControllerUrl = "http://localhost:"+randomServerPort+"/v1/api/atm/accountwithdrawl";
		URI uri = new URI(accountWithdrawlControllerUrl);
		
       HttpHeaders headers = new HttpHeaders();
       headers.set("X-COM-PERSIST", "true"); 
		
       HttpEntity<AccountWithdrawlRequest> request = new HttpEntity(withdrawlRequest, headers);
       ResponseEntity<AccountDetailsResponse> result = null;
       int resultStatusCode = 0; 
       String	resultString = "";
		try { 
			restTemplate.postForEntity(uri, request, String.class);
        
		} catch (HttpClientErrorException clientException) {
			resultStatusCode = clientException.getRawStatusCode();
			resultString = clientException.getResponseBodyAsString();
}
       Assertions.assertEquals(400, resultStatusCode);
       Assertions.assertEquals(true, resultString.contains("Please enter amount in multiples of 5"));
			      

	}

}
