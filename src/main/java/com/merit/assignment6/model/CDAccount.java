package com.merit.assignment6.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class CDAccount extends BankAccount{
	
	private int term;
	//private long accountHolder;

	public CDAccount() {
		super();
	}
	
	public CDAccount(double balance, double interestRate, long accountNumber, Date accountOpenedOn) {
		super(balance, interestRate, accountNumber, accountOpenedOn);
	}

	public CDAccount(double openingBalance, double interestRate, int term) {
		super(openingBalance, interestRate);
		this.term = term;
	}
	
	@Override
	public boolean withdraw(double amount) {
		return false;
	}
	
	public boolean deposit(double amount) {
		return false;
	}
	
	public int getTerm() {
		return this.term;
	}
	
	private long accountHolder;

	public long getAccountHolder() {
		return this.accountHolder;
	}

	public void setAccountHolder(long n) {
		this.accountHolder = n;
	}
	
	
	
	
}
