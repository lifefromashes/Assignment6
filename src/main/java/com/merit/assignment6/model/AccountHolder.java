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
import javax.validation.constraints.Size;

import com.merit.assignment6.exceptions.ExceedsAvailableBalanceException;
import com.merit.assignment6.exceptions.ExceedsCombinedBalanceLimitException;

@Entity
@Table(name = "accountholders")
public class AccountHolder implements Comparable<AccountHolder> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@NotBlank(message = "First Name is required")
	private String firstName;

	private String middleName;

	@NotBlank(message = "Last Name is required")
	private String lastName;

	@Size(min = 9, max = 11)
	@NotBlank(message = "SSN is required")
	private String ssn;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private AccountHolderContactDetails accountHolderContactDetails;

	@OneToMany(cascade = CascadeType.ALL)
	private List<CheckingAccount> checkingAccounts;

	@OneToMany(cascade = CascadeType.ALL)
	private List<SavingsAccount> savingsAccounts;

	@OneToMany(cascade = CascadeType.ALL)
	private List<CDAccount> cdAccounts;

	public AccountHolder() {
		this.checkingAccounts = new ArrayList<>();
		this.savingsAccounts = new ArrayList<>();
		this.cdAccounts = new ArrayList<>();
		// this.accountHolderContactInfo = new AccountHolderContactInfo();
	}

	public boolean addCheckingAccount(CheckingAccount chkacc) throws ExceedsCombinedBalanceLimitException {
		if (chkacc == null) {
			return false;
		}
		if (getCheckingBalance() + getSavingsBalance() + chkacc.getBalance() >= 250000) {
			throw new ExceedsCombinedBalanceLimitException("Unable To Process");
		}
		checkingAccounts.add(chkacc);
		chkacc.setAccountHolder(this.id);
		return true;
	}

	public boolean addSavingsAccount(SavingsAccount savacc) throws ExceedsCombinedBalanceLimitException {
		if (savacc == null) {
			return false;
		}
		if (getCheckingBalance() + getSavingsBalance() + savacc.getBalance() >= 250000) {
			throw new ExceedsCombinedBalanceLimitException("Unable to Process");
		}
		savingsAccounts.add(savacc);
		savacc.setAccountHolder(this.id);
		return true;
	}

	public boolean addCDAccount(CDAccount cdacc) {
		if (cdacc == null) {
			return false;
		}
		cdAccounts.add(cdacc);
		cdacc.setAccountHolder(this.id);
		return true;
	}

	public double getCheckingBalance() {
		double sum = 0;
		for (BankAccount b : checkingAccounts) {
			sum += b.getBalance();
		}
		return sum;
	}

	public double getSavingsBalance() {
		double sum = 0;
		for (BankAccount b : savingsAccounts) {
			sum += b.getBalance();
		}
		return sum;
	}

	public double getCDBalance() {
		double sum = 0;
		for (BankAccount b : cdAccounts) {
			sum += b.getBalance();
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String s) {
		this.firstName = s;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String s) {
		this.middleName = s;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String s) {
		this.lastName = s;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<CheckingAccount> getCheckingAccounts() {
		return checkingAccounts;
	}

	public void setCheckingAccounts(List<CheckingAccount> a) {
		this.checkingAccounts = a;
	}

	public List<SavingsAccount> getSavingsAccounts() {
		return savingsAccounts;
	}

	public void setSavingsAccounts(List<SavingsAccount> a) {
		this.savingsAccounts = a;
	}

	public List<CDAccount> getCdAccounts() {
		return cdAccounts;
	}

	public void setCdAccounts(List<CDAccount> a) {
		this.cdAccounts = a;
	}

	public AccountHolderContactDetails getAccountHolderContactDetails() {
		return accountHolderContactDetails;
	}

	public void setAccountHolderContactInfo(AccountHolderContactDetails accountHolderContactDetails) {
		this.accountHolderContactDetails = accountHolderContactDetails;
	}

	public int getNumberCheckingAccounts() {
		return this.checkingAccounts.size();
	}

	public int getNumberSavingsAccounts() {
		return this.savingsAccounts.size();
	}

	public int getNumberCDAccounts() {
		return this.cdAccounts.size();
	}

	@Override
	public int compareTo(AccountHolder other) {
		int mySum = (int) getCombinedBalance();
		int otherSum = (int) other.getCombinedBalance();
		return mySum - otherSum;
	}

}
