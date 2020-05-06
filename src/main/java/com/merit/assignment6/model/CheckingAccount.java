package com.merit.assignment6.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CheckingAccount extends BankAccount {

	static final double DEFAULT_INTEREST_RATE = .0001;

	// @Column(name = "user_id") //foreign key to link to AccountHolders table
	private Integer userId;

	public CheckingAccount() {
	}

	public CheckingAccount(double openingBalance) {
		super(openingBalance, DEFAULT_INTEREST_RATE);
	}

	public CheckingAccount(BankAccount bankAccount) {
		super(bankAccount.getBalance(), bankAccount.getInterestRate(), bankAccount.getAccountNumber(),
				bankAccount.getAccountOpenedOn());

	}

	public CheckingAccount(double balance, double interestRate, long accountNumber, Date accountOpenedOn) {
		super(balance, interestRate, accountNumber, accountOpenedOn);
	}

	private long accountHolder;

	public long getAccountHolder() {
		return this.accountHolder;
	}

	public void setAccountHolder(long n) {
		this.accountHolder = n;
	}

}
