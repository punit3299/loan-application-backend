package com.loan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.loan.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select c from Customer c where c.email=?1 or c.adhaar=?2 or c.pan=?3 or c.phone=?4")
	Customer checkCustomer(String email,long adhaar,String pan,long phone);

}




