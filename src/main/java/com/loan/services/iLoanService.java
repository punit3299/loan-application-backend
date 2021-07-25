package com.loan.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.loan.models.Customer;
import com.loan.models.Loan;
import com.loan.models.Transaction;

public interface iLoanService {

	public Loan applyLoan(Loan l);

	public List<Loan> getLoansByCustId(int custId);

	public Loan getLoanById(int id);

	public void deleteLoanById(int loanId);

}
