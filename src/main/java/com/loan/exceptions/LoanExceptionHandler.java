package com.loan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LoanExceptionHandler {
	
		//User Not Found Exception
	
		@ExceptionHandler({UserNotFoundException.class})
		public ResponseEntity<Object> Userexception(Exception ex) 
		{
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}

		//Loan Not Found Exception
		
		@ExceptionHandler({LoanNotFoundException.class})
		public ResponseEntity<Object> Loanexception(Exception ex) 
		{
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		
}
