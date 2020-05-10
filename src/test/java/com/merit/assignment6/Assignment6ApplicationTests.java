package com.merit.assignment6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.merit.assignment6.exceptions.ExceedsCombinedBalanceLimitException;
import com.merit.assignment6.exceptions.NegativeAmountException;
import com.merit.assignment6.model.AccountHolder;
import com.merit.assignment6.model.CheckingAccount;
import com.merit.assignment6.model.SavingsAccount;
import com.merit.assignment6.repository.AccountHolderRepository;
import com.merit.assignment6.repository.CheckingAccountRepository;
import com.merit.assignment6.repository.SavingsAccountRepository;



@SpringBootTest
class Assignment6ApplicationTests {

	@Autowired
	AccountHolderRepository accountHolderRepository;

	@Autowired
	CheckingAccountRepository checkingAccountRepository;
	
	@Autowired
	SavingsAccountRepository savingsAccountRepository;

	@PersistenceContext
	EntityManager entityManager;

	@Test
	void insertAccountHolder() {
		AccountHolder accountHolder = new AccountHolder();
		accountHolder.setFirstName("FirstName test");
		accountHolder.setLastName("LastName test");
		accountHolder.setSsn("7896541235");

		accountHolderRepository.save(accountHolder);

		AccountHolder dbAccHolder = accountHolderRepository.findById(accountHolder.getId());
		assertNotNull(dbAccHolder);

	}
	
//	@Test
//	public void createCheckingAccount() throws ExceedsCombinedBalanceLimitException, NegativeAmountException {
//		AccountHolder accountHolder = new AccountHolder();
//		CheckingAccount chkAcc = new CheckingAccount();
//		accountHolder.addCheckingAccount(chkAcc);
//		
//		chkAcc.deposit(3000);
//		
//		checkingAccountRepository.save(chkAcc);
//		
//		AccountHolder dbChk = accountHolderRepository.findById(accountHolder.getId());
//		
//		assertNotNull(dbChk.getCheckingBalance());
//	}

	
	@Test
	public void createSavingsAccount() throws ExceedsCombinedBalanceLimitException, NegativeAmountException {
		AccountHolder accountHolder = new AccountHolder();
		SavingsAccount savAcc = new SavingsAccount();
		accountHolder.addSavingsAccount(savAcc);
		
		savAcc.deposit(3000);
		
		savingsAccountRepository.save(savAcc);
		
		assertNotNull(savAcc);

		
		
	}
	
//	@Test
//	public void findAccountHolderById() {
//		AccountHolder accountHolder = new AccountHolder();
//		accountHolder.setFirstName("FirstName test");
//		accountHolder.setLastName("LastName test");
//		accountHolder.setSsn("7896541235");
//		accountHolder.setId(4);
//		
//		accountHolderRepository.save(accountHolder);
//		AccountHolder dbAcc = accountHolderRepository.findById(accountHolder.getId());
//
//		assertNotNull(dbAcc);
//		}
//	}
}


