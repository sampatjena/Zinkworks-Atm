package com.zinkworks.enterprise.solutions.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.zinkworks.enterprise.solutions.dto.AccountDetailsRequest;
import com.zinkworks.enterprise.solutions.dto.AccountDetailsResponse;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class AccountDetailsControllerImplTest {
	
	@LocalServerPort
	 int randomServerPort;
	 
	 final RestTemplate restTemplate = new RestTemplate();

	@Test
	void testGetAccountDetailsForCorrectAccountAndPin() throws Exception {
		AccountDetailsRequest detailsRequest = new AccountDetailsRequest();
		detailsRequest.setAccountNumber("123456789");
		detailsRequest.setAccountPin("1234");
		
		AccountDetailsResponse response = new AccountDetailsResponse();
		response.setAccountBalanceAmount(800.0);
		response.setAccountOverdraftAmount(200.0);
						
		final String accountDetailsControllerUrl = "http://localhost:"+randomServerPort+"/v1/api/atm/accountdetails";
		URI uri = new URI(accountDetailsControllerUrl);
		
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true"); 
		
        HttpEntity<AccountDetailsRequest> request = new HttpEntity(detailsRequest, headers);
        ResponseEntity<AccountDetailsResponse> result = restTemplate.postForEntity(uri, request, AccountDetailsResponse.class);
                
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals(true, result.getBody().getAccountBalanceAmount()==800.0);
		
	}
	
	@Test
	void testGetAccountDetailsForInCorrectAccountAndPin() throws Exception {
		AccountDetailsRequest detailsRequest = new AccountDetailsRequest();
		detailsRequest.setAccountNumber("123456789");
		detailsRequest.setAccountPin("1238");

		final String accountDetailsControllerUrl = "http://localhost:"+randomServerPort+"/v1/api/atm/accountdetails";
		URI uri = new URI(accountDetailsControllerUrl);
		
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true"); 
		
        HttpEntity<AccountDetailsRequest> request = new HttpEntity(detailsRequest, headers);
        ResponseEntity<AccountDetailsResponse> result = null;
        int resultStatusCode = 0; 
        String	resultString = "";
		try {
			result = restTemplate
						.postForEntity(uri, request, AccountDetailsResponse.class);
		} catch (HttpClientErrorException clientException) {
						resultStatusCode = clientException.getRawStatusCode();
						resultString = clientException.getResponseBodyAsString();
		}
                
		Assertions.assertEquals(404, resultStatusCode);
        Assertions.assertEquals(true,resultString.contains("Incorrect Account No or Atm Pin."));
		
	}

}
