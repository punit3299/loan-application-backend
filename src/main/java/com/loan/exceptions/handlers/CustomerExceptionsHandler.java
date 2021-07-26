package com.loan.exceptions.handlers;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.loan.exceptions.CustomerAlreadyRegisteredException;
import com.loan.exceptions.CustomerNotFoundException;

@RestControllerAdvice
public class CustomerExceptionsHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException ex, WebRequest request) {
		logger.error(ex.getMessage(), ex);
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CustomerAlreadyRegisteredException.class)
	public ResponseEntity<Object> handleCustomerAlreadyRegisteredException(CustomerAlreadyRegisteredException ex,
			WebRequest request) {
		logger.warn(ex.getMessage());
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
	}
}
