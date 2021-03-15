package com.loan.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.loan.dao.CustomerRepository;
import com.loan.dao.LoanRepository;
import com.loan.dao.TransactionRepository;
import com.loan.entity.Customer;
import com.loan.entity.Loan;
import com.loan.entity.Transaction;
import com.loan.exceptions.UserNotFoundException;

@Service
public class LoanService implements iLoanService {

	@Autowired
	private CustomerRepository dao;
	@Autowired
	private LoanRepository loandao;
	@Autowired
	private TransactionRepository transdao;
	@Autowired
	private JavaMailSender javaMailSender;

	private Logger logger = Logger.getLogger(getClass());

	// Add Customer
	public Customer addCustomer(Customer c) {
		Customer cust = dao.checkCustomer(c.getEmail(), c.getAdhaar(), c.getPan(), c.getPhone());
		System.out.println(cust);
		if (cust == null)
			return dao.save(c);
		else
			return null;
	}

	// Update Customer
	public Customer updateCustomer(Customer c) {
		return dao.save(c);
	}

	// Fetching all Customers
	public Iterable<Customer> getAllCustomers(Pageable pageable) {
		return dao.findAll(pageable);
	}

	// Fetching Customer By Customer Id
	public Customer getCustomerById(int custId) {
		if (dao.findById(custId).isPresent()) {
			return dao.findById(custId).get();
		} else {
			logger.error("User_Not_Found");
			throw new UserNotFoundException("User Not Found");
		}
	}

	// Applying Loan
	public Loan applyLoan(Loan loan) {
		if (dao.findById(loan.getId()).isPresent()) // Checking if this loan obj is related to any customer or not
		{
			loan.setCust(dao.findById(loan.getId()).get());
			return loandao.save(loan);
		} else {
			logger.error("User_Not_Found");
			throw new UserNotFoundException("User Not Found");
		}
	}

	// Fetching Loans by Customer Id
	public List<Loan> getLoansByCustId(int custId) {
		if (dao.findById(custId).isPresent()) {
			return loandao.getLoansByCustId(custId);
		} else {
			logger.error("User_Not_Found");
			throw new UserNotFoundException("User Not Found");
		}
	}

	// Delete a loan object by loanId
	public void deleteLoanById(int loanId) {
		loandao.deleteById(loanId);
	}

	// Getting a loan obj by loanId
	public Optional<Loan> getLoanById(int id) {
		return loandao.findById(id);
	}

	// Adding Transaction
	public Transaction addTransaction(Transaction trans) {
		if (dao.findById(trans.getId()).isPresent()) {
			trans.setCust(dao.findById(trans.getId()).get());
			return transdao.save(trans);
		} else {
			logger.error("User_Not_Found");
			throw new UserNotFoundException("User Not Found");
		}
	}

	// Fetching Transactions by Customer Id
	public List<Transaction> getTransByCustId(int custId) {
		if (dao.findById(custId).isPresent()) {
			return transdao.getTransById(custId);
		} else {
			logger.error("User_Not_Found");
			throw new UserNotFoundException("User Not Found");
		}
	}

	// Send Mail
	public void sendJavaMail(String email) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom("edu9vision@gmail.com");
		mail.setSubject("Borrow Loan Company");
		mail.setText("Thanks for Contacting Us...We will Contact u soon");

		javaMailSender.send(mail);
	}

	@Override
	public Integer verifyLogin(Customer c) {
		for (Customer cust : dao.findAll()) {
			if (cust.getEmail().equals(c.getEmail()) && cust.getPassword().equals(c.getPassword())) {
				return cust.getId();
			}
		}
		return null;
	}

}
