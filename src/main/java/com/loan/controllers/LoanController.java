package com.loan.controllers;

import org.apache.log4j.Logger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loan.exceptions.LoanNotFoundException;
import com.loan.models.Customer;
import com.loan.models.Loan;
import com.loan.models.Transaction;
import com.loan.services.iLoanService;
import com.loan.services.impl.LoanServiceImpl;

@RestController
@RequestMapping("/loan")
@CrossOrigin(origins = "*")
public class LoanController {

	@Autowired(required = true)
	private iLoanService loanService;

	private Logger logger = Logger.getLogger(getClass());

	// Applying Loan

	@PostMapping("/")
	public Loan applyLoan(@RequestBody Loan loan) {

		Loan l = loanService.applyLoan(loan);
		if (l != null) {
			logger.info("Loan Applied Successfully");
			return l;
		} else {
			logger.info("Loan Application Failed");
			return null;
		}
	}

	// Fetching Loans by Customer Id

	@GetMapping("/customer/{id}")
	public List<Loan> getLoansByCustId(@PathVariable int id) {
		return loanService.getLoansByCustId(id);
	}

	// Account Foreclosure

	@DeleteMapping("/foreclose/{loanId}")
	public void forecloseLoan(@PathVariable int loanId) {
		Loan loan = loanService.getLoanById(loanId);
		if (loan != null) {
			loanService.deleteLoanById(loanId);
			logger.info("Loan Foreclosed Successfully");
		} else {
			logger.error("Loan Foreclosure Unsuccessful");
			throw new LoanNotFoundException("Loan not Found");
		}
	}

}
