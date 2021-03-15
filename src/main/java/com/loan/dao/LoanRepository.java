package com.loan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.loan.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

	@Query("select l from Loan l where l.id=?1")   //?1 denotes first parameter var of function
	List<Loan> getLoansByCustId(int custId);

}
