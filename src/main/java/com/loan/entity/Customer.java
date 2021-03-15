package com.loan.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "LOAN_SPRING_CUSTOMER")
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String fname;
	private String lname;
	private String gender;
	private long phone;
	private String email;
	private String password;
	private String confirmPassword;
	private float salary;
	private long adhaar;
	private String pan;
	private int loansTaken;
	private int transCount;
	private double walletAmt;

	public Customer(String fname, String lname, String gender, long phone, String email, String password,
			String confirmPassword, float salary, long adhaar, String pan, int loansTaken, int transCount,
			double walletAmt) {
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.salary = salary;
		this.adhaar = adhaar;
		this.pan = pan;
		this.loansTaken = loansTaken;
		this.transCount = transCount;
		this.walletAmt = walletAmt;
	}

}
