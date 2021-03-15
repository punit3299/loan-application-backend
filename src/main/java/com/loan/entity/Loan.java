package com.loan.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "LOAN_SPRING_LOAN")
public class Loan implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int loanId;
	private double loanAmt;
	private String loanType;
	private int duration;
	private int id; // Cutomer Id
	private double monthlyEMI;

	@ManyToOne
	@JoinColumn(name = "id", insertable = false, updatable = false)
	private Customer cust;

	public Loan(double loanAmt, String loanType, int duration, int id, Customer cust) {
		this.loanAmt = loanAmt;
		this.loanType = loanType;
		this.duration = duration;
		this.id = id;
		this.cust = cust;
	}

}
