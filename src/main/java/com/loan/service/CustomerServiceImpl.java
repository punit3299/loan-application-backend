package com.loan.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.loan.dao.CustomerRepository;
import com.loan.entity.Customer;
import com.loan.exceptions.UserNotFoundException;

@Service
@Primary
public class CustomerServiceImpl implements iCustomerService {

	@Autowired
	private CustomerRepository customerDao;
	
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public Customer addCustomer(Customer c) {
		Customer cust = customerDao.checkCustomer(c.getEmail(), c.getAdhaar(), c.getPan(), c.getPhone());
		System.out.println(cust);
		if (cust == null)
			return customerDao.save(c);
		else
			return null;
	}
	
	@Override
	public Integer verifyLogin(Customer c) {
		for (Customer cust : customerDao.findAll()) {
			if (cust.getEmail().equals(c.getEmail()) && cust.getPassword().equals(c.getPassword())) {
				return cust.getId();
			}
		}
		return null;
	}

	public Customer updateCustomer(Customer c) {
		return customerDao.save(c);
	}

	@Override
	public Iterable<Customer> getAllCustomers(Pageable pageable) {
		return customerDao.findAll(pageable);
	}

	@Override
	public Customer getCustomerById(int custId) {
		if (customerDao.findById(custId).isPresent()) {
			return customerDao.findById(custId).get();
		} else {
			logger.error("User_Not_Found");
			throw new UserNotFoundException("User Not Found");
		}
	}

}
