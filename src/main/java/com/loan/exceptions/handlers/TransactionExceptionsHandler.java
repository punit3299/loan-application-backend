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

import com.loan.exceptions.TransactionFailedException;
import com.loan.exceptions.TransactionsNotFoundException;

@RestControllerAdvice
public class TransactionExceptionsHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(TransactionFailedException.class)
	public ResponseEntity<Object> handleTransactionFailedException(TransactionFailedException ex, WebRequest request) {
		logger.error(ex.getMessage(), ex);
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TransactionsNotFoundException.class)
	public ResponseEntity<Object> handleTransactionsNotFoundException(TransactionsNotFoundException ex,
			WebRequest request) {
		logger.error(ex.getMessage(), ex);
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
	}

}
