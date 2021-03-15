package com.loan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.loan.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	
	@Query("select t from Transaction t where t.id=?1")
	List<Transaction> getTransById(int id);
	
}
