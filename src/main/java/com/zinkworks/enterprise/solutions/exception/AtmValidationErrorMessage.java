package com.zinkworks.enterprise.solutions.exception;

import java.util.ArrayList;
import java.util.List;

public class AtmValidationErrorMessage {
	
	private int statusCode;
	private String errorDateTime;
	private List<String> validationErrors = new ArrayList<>();
	
	public AtmValidationErrorMessage()
	{
		
	}

	public AtmValidationErrorMessage(int statusCode, String errorDateTime,
			List<String> validationErrors) {
		super();
		this.statusCode = statusCode;
		this.errorDateTime = errorDateTime;
		this.validationErrors = validationErrors;
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

	public List<String> getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(List<String> validationErrors) {
		this.validationErrors = validationErrors;
	}
	  
	
	
	

}
