package com.loan.services;

import org.springframework.data.domain.Pageable;

import com.loan.models.Customer;

public interface iCustomerService {

	public Integer verifyLogin(Customer c);

	public Customer addCustomer(Customer c);

	public Customer updateCustomer(Customer c);

	public Iterable<Customer> getAllCustomers(Pageable pageable);

	public Customer getCustomerById(int custId);
}
