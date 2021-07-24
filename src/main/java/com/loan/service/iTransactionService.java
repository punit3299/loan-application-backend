package com.loan.service;

import java.util.List;

import com.loan.entity.Transaction;

public interface iTransactionService {

	public Transaction addTransaction(Transaction trans);

	public List<Transaction> getTransactionsByCustId(int custId);
}
