package com.loan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.loan.entity.Customer;
import com.loan.entity.Loan;
import com.loan.entity.Transaction;

public interface iLoanService {

	public Loan applyLoan(Loan l);

	public List<Loan> getLoansByCustId(int custId);

	public Loan getLoanById(int id);

	public void deleteLoanById(int loanId);

}
