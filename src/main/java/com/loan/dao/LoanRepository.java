package com.loan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.loan.models.Customer;
import com.loan.models.Loan;

@Repository
public interface LoanRepository extends PagingAndSortingRepository<Loan, Integer> {

	@Query("select l from Loan l where l.id=?1")
	Customer findByCustomerId(int custId);
}
