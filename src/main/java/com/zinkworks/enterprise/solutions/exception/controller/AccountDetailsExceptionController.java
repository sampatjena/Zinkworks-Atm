package com.zinkworks.enterprise.solutions.exception.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.zinkworks.enterprise.solutions.exception.AccountDetailsException;
import com.zinkworks.enterprise.solutions.exception.AtmBalanceException;
import com.zinkworks.enterprise.solutions.exception.AtmErrorMessage;
import com.zinkworks.enterprise.solutions.exception.AtmException;
import com.zinkworks.enterprise.solutions.exception.AtmValidationErrorMessage;

@ControllerAdvice
public class AccountDetailsExceptionController {
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM");
	
	@ExceptionHandler(AccountDetailsException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    protected ResponseEntity<AtmErrorMessage> handleAccountNotFound(
    		AccountDetailsException accountException , WebRequest request) {
		 AtmErrorMessage errorMessage = new AtmErrorMessage();
		 errorMessage.setStatusCode(HttpStatus.NOT_FOUND.value());
		 errorMessage.setErrorDateTime(dateFormat.format(new Date()));
		 errorMessage.setErrorMessage(accountException.getMessage());
		 return new ResponseEntity<AtmErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(AtmBalanceException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ResponseEntity<AtmErrorMessage> handleWithdrawlRequestNotValid(
    		AtmBalanceException atmException , WebRequest request) {
		 AtmErrorMessage errorMessage = new AtmErrorMessage();
		 errorMessage.setStatusCode(HttpStatus.BAD_REQUEST.value());
		 errorMessage.setErrorDateTime(dateFormat.format(new Date()));
		 errorMessage.setErrorMessage(atmException.getMessage());
		 return new ResponseEntity<AtmErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(AtmException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ResponseEntity<AtmErrorMessage> handleAtmCurrencyNotFound(
    		AtmException atmException , WebRequest request) {
		 AtmErrorMessage errorMessage = new AtmErrorMessage();
		 errorMessage.setStatusCode(HttpStatus.BAD_REQUEST.value());
		 errorMessage.setErrorDateTime(dateFormat.format(new Date()));
		 errorMessage.setErrorMessage(atmException.getMessage());
		 return new ResponseEntity<AtmErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
	  public ResponseEntity<AtmErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
		AtmErrorMessage errorMessage = new AtmErrorMessage();
		 errorMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		 errorMessage.setErrorDateTime(dateFormat.format(new Date()));
		 errorMessage.setErrorMessage(ex.getMessage());
	    return new ResponseEntity<AtmErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public AtmValidationErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        
        List<String> errorList = new ArrayList<>();
        result.getFieldErrors().forEach((fieldError) -> {
        	errorList.add(fieldError.getObjectName()+"."+fieldError.getField()+" : " +fieldError.getDefaultMessage() +" : rejected value [" +fieldError.getRejectedValue() +"]" );
        });
        result.getGlobalErrors().forEach((fieldError) -> {
        	errorList.add(fieldError.getObjectName()+" : " +fieldError.getDefaultMessage() );
        });
        
        return new AtmValidationErrorMessage(HttpStatus.BAD_REQUEST.value(), dateFormat.format(new Date()) , errorList);
    }
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid request format")
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<AtmErrorMessage> handleException(HttpMessageNotReadableException ex , WebRequest request) {
		AtmErrorMessage errorMessage = new AtmErrorMessage();
		 errorMessage.setStatusCode(HttpStatus.OK.value());
		 errorMessage.setErrorDateTime(dateFormat.format(new Date()));
		 errorMessage.setErrorMessage(ex.getLocalizedMessage());
	    return new ResponseEntity<AtmErrorMessage>(errorMessage,HttpStatus.BAD_REQUEST);
    }


}
