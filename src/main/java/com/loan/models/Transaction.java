package com.loan.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transId;
	private Timestamp transTime;
	private String mssg;

	@ManyToOne
	@JoinColumn(name = "loanId", insertable = false, updatable = false)
	private Loan loan;

	public Transaction() {

	}

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public Timestamp getTransTime() {
		return transTime;
	}

	public void setTransTime(Timestamp transTime) {
		this.transTime = transTime;
	}

	public String getMssg() {
		return mssg;
	}

	public void setMssg(String mssg) {
		this.mssg = mssg;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}
}
