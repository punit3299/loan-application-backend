package com.loan.controllers;

import org.apache.log4j.Logger;
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

import com.loan.models.Customer;
import com.loan.services.iCustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

	@Autowired(required = true)
	iCustomerService customerService;

	private Logger logger = Logger.getLogger(getClass());

	@PostMapping
	public Customer addCustomer(@RequestBody Customer c) {
		Customer cust = customerService.addCustomer(c);
		if (cust != null) {
			logger.info("Customer Registered Successfully");
			return cust;
		} else {
			logger.error("Customer Registration Failed");
			return null;
		}
	}

	// Updating Customer

	@PutMapping
	public Customer updateCustomer(@RequestBody Customer c) {
		Customer cust = customerService.updateCustomer(c);
		if (cust != null) {
			logger.info("Customer Updated Successfully");
			return cust;
		} else {
			logger.error("Customer Updation Failed");
			return null;
		}
	}

	// Fetching all Customers

	@GetMapping
	public Iterable<Customer> getAllCustomers(@RequestParam("page") int pageNo, @RequestParam("size") int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return customerService.getAllCustomers(pageable);
	}

	// Customer Login

	@PostMapping("/login")
	public Integer verifyLogin(@RequestBody Customer c) {

		Integer id = null;

		id = customerService.verifyLogin(c);

		if (id != null) {
			logger.info("Logged In Successfully");
			return id;
		} else {
			logger.error("Login Failed");
			return 0;
		}
	}

	// Fetching Customer By Customer Id

	@GetMapping("/{id}")
	public Customer getCustomer(@PathVariable int id) {
		return customerService.getCustomerById(id);
	}
}
