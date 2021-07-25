package com.loan.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.loan.dao.CustomerRepository;
import com.loan.dao.LoanRepository;
import com.loan.dao.TransactionRepository;
import com.loan.exceptions.CustomerNotFoundException;
import com.loan.models.Customer;
import com.loan.models.Loan;
import com.loan.models.Transaction;
import com.loan.services.iLoanService;

@Service
@Primary
public class LoanServiceImpl implements iLoanService {

	@Autowired
	private LoanRepository loanDao;

	@Autowired
	private CustomerRepository customerDao;

	private Logger logger = Logger.getLogger(getClass());

	// Apply Loan
	public Loan applyLoan(Loan loan) {
		Customer customer = loan.getCustomer();
		if (customer != null) {
			customer.addLoan(loan);
			return loanDao.save(loan);
		} else {
			logger.error("User Not Found");
			throw new CustomerNotFoundException("User Not Found");
		}
	}

	// Fetching Loans by Customer Id
	public List<Loan> getLoansByCustId(int custId) {
		Optional<Customer> customer = customerDao.findById(custId);
		if (customer.isPresent()) {
			return customer.get().getLoans();
		} else {
			logger.error("User Not Found");
			throw new CustomerNotFoundException("User Not Found");
		}
	}

	// Delete a loan object by loanId
	public void deleteLoanById(int loanId) {
		loanDao.deleteById(loanId);
	}

	public Loan getLoanById(int loanId) {
		return loanDao.findById(loanId).get();
	}

}
