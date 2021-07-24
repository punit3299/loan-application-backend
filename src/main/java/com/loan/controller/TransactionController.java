package com.loan.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loan.entity.Transaction;
import com.loan.service.iTransactionService;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "*")
public class TransactionController {

	@Autowired
	private iTransactionService transactionService;

	@PostMapping("/")
	public Transaction addTransaction(@RequestBody Transaction trans) {
		return transactionService.addTransaction(trans);
	}

	@GetMapping("/customer/{id}")
	public List<Transaction> getTransactionsByCustId(@PathVariable int id) {
		return transactionService.getTransactionsByCustId(id);
	}
}
