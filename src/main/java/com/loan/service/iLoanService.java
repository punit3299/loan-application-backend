package com.loan.service;

import java.util.List;
import java.util.Optional;

import com.loan.entity.Customer;
import com.loan.entity.Loan;
import com.loan.entity.Transaction;

public interface iLoanService {
	
	public Customer addCustomer(Customer c);
	public Customer updateCustomer(Customer c);
	public Iterable<Customer> getAllCustomers();
	public Customer getCustomerById(int custId);
	public Loan applyLoan(Loan l);
	public List<Loan> getLoansByCustId(int custId);
	public Optional<Loan> getLoanById(int id);
	public void deleteLoanById(int loanId);
	public Transaction addTransaction(Transaction trans);
	public List<Transaction> getTransByCustId(int custId);
	
}
