package com.loan.entity;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name = "LOAN_SPRING_TRANSACTION")
public class Transaction implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transId;
	private Timestamp transTime;
	private String mssg;
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id", insertable = false, updatable = false)
	private Customer cust;

	public Transaction(Timestamp transTime, String mssg, int id, Customer cust) {
		this.transTime = transTime;
		this.mssg = mssg;
		this.id = id;
		this.cust = cust;
	}
	
	


}
