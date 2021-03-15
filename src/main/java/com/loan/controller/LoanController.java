package com.loan.controller;

import org.apache.log4j.Logger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loan.entity.Customer;
import com.loan.entity.Loan;
import com.loan.entity.Transaction;
import com.loan.exceptions.LoanNotFoundException;
import com.loan.service.LoanService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class LoanController {

	@Autowired
	private LoanService service;

	private Logger logger = Logger.getLogger(getClass());

	// Adding Customer

	@PostMapping("/signup")
	public Customer addCustomer(@RequestBody Customer c) {
		Customer cust = service.addCustomer(c);
		if (cust != null) {
			logger.info("Customer_Registered_Successfully");
			return cust;
		} else {
			logger.error("Customer_Registration_Failed");
			return null;
		}
	}

	// Updating Customer

	@PutMapping("/updateCust")
	public Customer updateCustomer(@RequestBody Customer c) {
		Customer cust = service.updateCustomer(c);
		if (cust != null) {
			logger.info("Customer_Updated_Successfully");
			return cust;
		} else {
			logger.error("Customer_Updation_Failed");
			return null;
		}
	}

	// Fetching all Customers

	@GetMapping("/all")
	public Iterable<Customer> getAllCustomers(@RequestParam("page") int pageNo, @RequestParam("size") int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return service.getAllCustomers(pageable);
	}

	// Customer Login

	@PostMapping("/login")
	public Integer verifyLogin(@RequestBody Customer c) {

		Integer id = null;

		id = service.verifyLogin(c);

		if (id != null) {
			logger.info("Logged_In_Successfully");
			return id;
		} else {
			logger.error("Login_Failed");
			return 0;
		}
	}

	// Fetching Customer By Customer Id

	@GetMapping("/main/{id}")
	public Customer getCustomerById(@PathVariable int id) {

		return service.getCustomerById(id);
	}

	// Applying Loan

	@PostMapping("/loan")
	public Loan applyLoan(@RequestBody Loan loan) {

		Loan l = service.applyLoan(loan);
		if (l != null) {
			logger.info("Loan_Applied_Successfully");
			return l;
		} else {
			logger.info("Failed_To_Apply_Loan");
			return null;
		}
	}

	// Fetching Loans by Customer Id

	@GetMapping("/getLoans/{id}")
	public List<Loan> getLoansByCustId(@PathVariable int id) {

		List<Loan> list = service.getLoansByCustId(id);
		if (list != null) {
			return list;
		} else {
			logger.error("Failed_To_Get_Loan_Details");
			return null;
		}
	}

	// Adding Transaction

	@PostMapping("/addTrans")
	public Transaction addTransaction(@RequestBody Transaction trans) {
		Transaction t = service.addTransaction(trans);
		if (t != null) {
			logger.info("Transaction_Successfull");
			return t;
		} else {
			logger.info("Transaction_Failed");
			return null;
		}

	}

	// Fetching Transactions by Customer Id

	@GetMapping("/getTrans/{id}")
	public List<Transaction> getTransByCustId(@PathVariable int id) {

		List<Transaction> list = service.getTransByCustId(id);
		if (list != null) {
			return list;
		} else {
			logger.error("Failed_To_Get_Transaction_Details");
			return null;
		}
	}

	// Account Foreclosure

	@PostMapping("/forecloseAcct/{loanId}")
	public void forecloseAccount(@PathVariable int loanId) {
		if (service.getLoanById(loanId).isPresent()) {
			service.deleteLoanById(loanId);
			logger.info("Loan_Foreclosed_Successfully");
		} else {
			logger.error("Loan_Foreclosure_Unsuccessful");
			throw new LoanNotFoundException("Loan not Found");
		}
	}

	// Send Mail

	@PostMapping("/mail")
	public void sendMail(@RequestBody String email) {
		try {
			service.sendJavaMail(email);
			logger.info("Mail_Sent_Successfully");
		} catch (Exception e) {
			logger.error("Something went wrong. You may have wrong credentials in application.properties.");
		}
	}

}
