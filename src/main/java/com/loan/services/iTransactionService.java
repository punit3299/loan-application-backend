package com.loan.services;

import java.util.List;

import com.loan.models.Transaction;

public interface iTransactionService {

	public Transaction addTransaction(Transaction trans);

	public List<Transaction> getTransactionsByCustId(int custId);
}
