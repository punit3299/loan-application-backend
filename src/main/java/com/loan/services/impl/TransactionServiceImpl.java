package com.loan.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.TransactionalException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.loan.dao.CustomerRepository;
import com.loan.dao.LoanRepository;
import com.loan.dao.TransactionRepository;
import com.loan.exceptions.CustomerNotFoundException;
import com.loan.exceptions.LoanNotFoundException;
import com.loan.exceptions.TransactionFailedException;
import com.loan.exceptions.TransactionsNotFoundException;
import com.loan.models.Customer;
import com.loan.models.Loan;
import com.loan.models.Transaction;
import com.loan.services.iTransactionService;

@Service
@Primary
public class TransactionServiceImpl implements iTransactionService {

	@Autowired
	private CustomerRepository customerDao;

	@Autowired
	private LoanRepository loanDao;

	@Autowired
	private TransactionRepository transactionDao;

	private Logger logger = Logger.getLogger(getClass());

	@Override
	public Transaction addTransaction(Transaction transaction) {
		int loanId = transaction.getLoan().getLoanId();
		Loan loan = loanDao.findById(loanId).orElseThrow(() -> new LoanNotFoundException("Loan Not Found: " + loanId));
		loan.addTransaction(transaction);
		try {
			return transactionDao.save(transaction);
		} catch (Exception e) {
			throw new TransactionFailedException("Transaction Failed for LoanId: " + loanId);
		}
	}

	@Override
	public List<Transaction> getTransactionsByCustId(int customerId) {
		Customer customer = customerDao.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + customerId));
		try {
			List<Transaction> transactions = transactionDao.findTransactionsByCustomerId(customerId);
			return transactions;
		} catch (Exception e) {
			throw new TransactionsNotFoundException("Transactions not Found for Customer Id: " + customerId);
		}
	}

}
