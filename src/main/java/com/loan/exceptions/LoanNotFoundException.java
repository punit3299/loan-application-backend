package com.loan.exceptions;

public class LoanNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public LoanNotFoundException(String msg){
		super(msg);
	}
}
