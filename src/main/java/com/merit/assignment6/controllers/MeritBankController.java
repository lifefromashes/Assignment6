package com.merit.assignment6.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.merit.assignment6.model.AccountHolder;
import com.merit.assignment6.repository.AccountHolderContactDetailsRepository;
import com.merit.assignment6.repository.AccountHolderRepository;
import com.merit.assignment6.repository.CDAccountRepository;
import com.merit.assignment6.repository.CheckingAccountRepository;
import com.merit.assignment6.repository.SavingsAccountRepository;

@RestController
public class MeritBankController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AccountHolderRepository accountHolderRepository;
	
	@Autowired
	private AccountHolderContactDetailsRepository accountHolderContactDetailsRepository;
	
	@Autowired
	private CheckingAccountRepository checkingAccountRepository;
	
	@Autowired
	private SavingsAccountRepository savingsAccountRepository;
	
	@Autowired
	private CDAccountRepository cdAccountRepository;
	
	@PostMapping("/AccountHolders")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountHolder addAccountHolder(@RequestBody @Valid AccountHolder accountHolder) {
		accountHolderRepository.save(accountHolder);
		return accountHolder;
	}
	
	@GetMapping(value = "/AccountHolders")
	@ResponseStatus(HttpStatus.OK)
	public List<AccountHolder> getAccountHolders() {
		return accountHolderRepository.findAll();
	}
	
//	@GetMapping(value = "/AccountHolders/{id}")
//	public AccountHolder getAccountHolderById(@PathVariable(name = "id") long id) throws com.merit.assignment6.exceptions.NoSuchResourceFoundException {
//		AccountHolder accthold = accountHolderRepository.findById(id);
//		return accthold;
//	}

}
