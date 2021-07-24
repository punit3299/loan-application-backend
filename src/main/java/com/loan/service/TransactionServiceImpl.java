package com.loan.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.loan.dao.CustomerRepository;
import com.loan.dao.TransactionRepository;
import com.loan.entity.Customer;
import com.loan.entity.Loan;
import com.loan.entity.Transaction;
import com.loan.exceptions.LoanNotFoundException;
import com.loan.exceptions.UserNotFoundException;

@Service
@Primary
public class TransactionServiceImpl implements iTransactionService {

	@Autowired
	private CustomerRepository customerDao;

	@Autowired
	private TransactionRepository transactionDao;

	private Logger logger = Logger.getLogger(getClass());

	@Override
	public Transaction addTransaction(Transaction transaction) {
		Loan loan = transaction.getLoan();
		if (loan != null) {
			loan.addTransaction(transaction);
			return transactionDao.save(transaction);
		} else {
			logger.error("Loan Not Found");
			throw new LoanNotFoundException("Loan Not Found");
		}
	}

	@Override
	public List<Transaction> getTransactionsByCustId(int custId) {
		Optional<Customer> customer = customerDao.findById(custId);
		if (customer.isPresent()) {
			List<Transaction> transactions = transactionDao.findTransactionsByCustomerId(custId);
			return transactions;
		} else {
			logger.error("User Not Found");
			throw new UserNotFoundException("User Not Found");
		}
	}

}
