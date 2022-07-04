package com.zinkworks.enterprise.solutions.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AtmErrorMessage {
	
	private int statusCode;
	  private String errorDateTime;
	 private String errorMessage;
	
	  
	  
	public AtmErrorMessage() {
			}

	public AtmErrorMessage(int statusCode, String errorDateTime,
			String errorMessage) {
		super();
		this.statusCode = statusCode;
		this.errorDateTime = errorDateTime;
		this.errorMessage = errorMessage;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorDateTime() {
		return errorDateTime;
	}

	public void setErrorDateTime(String errorDateTime) {
		this.errorDateTime = errorDateTime;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	



	
	

	
	
	  
	  

}
