package com.merit.assignment6.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import com.meritamerica.assignment6.exceptions.ExceedsCombinedBalanceLimitException;

@Entity
@Table(name = "AccountHolders", catalog = "MeritAmerica")
public class AccountHolder implements Comparable<AccountHolder> {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private long id;
	
	@Column(name = "firstName")
	@NotBlank
	private String firstName;
	
	@Column(name = "middleName")
	@NotBlank
	private String middleName;
	
	@Column(name = "lastName")
	@NotBlank
	private String lastName;
	
	@Column(name = "ssn")
	@NotBlank
	private String ssn;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private AccountHolderContactDetails accountHolderContactDetails;
	
	
	@Transient
	private List<BankAccount> checkingAccounts;
	@Transient
	private List<BankAccount> savingsAccounts;
	@Transient
	private List<BankAccount> cdAccounts;
	
	
	public AccountHolder() {}
	

	public boolean addCheckingAccount(CheckingAccount checkingAccount) throws ExceedsCombinedBalanceLimitException {
		if (checkingAccount == null) {
			return false;
		}
		if (getCheckingBalance() + getSavingsBalance() + checkingAccount.getBalance() >= 250000) {
			throw new ExceedsCombinedBalanceLimitException("Unable to create Account");
		}
		checkingAccounts.add(checkingAccount);
		return true;
	}

	public boolean addSavingsAccount(SavingsAccount savingsAccount) throws ExceedsCombinedBalanceLimitException {
		if (savingsAccount == null) {
			return false;
		}
		if (getSavingsBalance() + getCheckingBalance() + savingsAccount.getBalance() >= 250000) {
			throw new ExceedsCombinedBalanceLimitException("Unable to create Account");
		}
		savingsAccounts.add(savingsAccount);
		return true;
	}

	public boolean addCDAccount(CDAccount cdAccount) {
		if (cdAccount == null)
			return false;

		cdAccounts.add(cdAccount);
		return true;
	}

	public double getCheckingBalance() {
		double sum = 0;
		for (BankAccount bankAcc : checkingAccounts) {
			sum += bankAcc.getBalance();
		}
		return sum;
	}

	public double getSavingsBalance() {
		double sum = 0;
		for (BankAccount savAcc : savingsAccounts) {
			sum += savAcc.getBalance();
		}
		return sum;
	}

	public double getCDBalance() {
		double sum = 0;
		for (BankAccount cdAcc : cdAccounts) {
			sum += cdAcc.getBalance();
		}
		return sum;
	}

	public double getCombinedBalance() {
		double sum = 0;
		sum += getCheckingBalance();
		sum += getSavingsBalance();
		sum += getCDBalance();
		return sum;
	}

	
	public long getId() {
		return id;
	}


	public AccountHolder setId(Integer id) {
		this.id = id;
		return this;
	}


	public String getFirstName() {
		return firstName;
	}


	public AccountHolder setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}


	public String getMiddleName() {
		return middleName;
	}


	public AccountHolder setMiddleName(String middleName) {
		this.middleName = middleName;
		return this;
	}


	public String getLastName() {
		return lastName;
	}


	public AccountHolder setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}


	public String getSsn() {
		return ssn;
	}


	public AccountHolder setSsn(String ssn) {
		this.ssn = ssn;
		return this;
	}
	
	public List<BankAccount> getCheckingAccounts() {
		return checkingAccounts;
	}

	public List<BankAccount> getSavingsAccounts() {
		return savingsAccounts;
	}

	public List<BankAccount> getCdAccounts() {
		return cdAccounts;
	}

	public int getNumOfCheckingAccounts() {
		return checkingAccounts.size();
	}

	public int getNumofSavingsAccounts() {
		return savingsAccounts.size();
	}

	public int getNumOfCDAccounts() {
		return cdAccounts.size();
	}

	@Override
	public int compareTo(AccountHolder other) {
		int mySum = (int) getCombinedBalance();
		int otherSum = (int) other.getCombinedBalance();
		return mySum - otherSum;
	}

	
	

}
