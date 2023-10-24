package com.poly.service;

import java.util.List;

import com.poly.entity.Account;

public interface AccountService {
	public List<Account> findAll() ;
	public Account findById(String username) ;
	public List<Account> getAdministrators() ;
	
	Account createAccount(Account acc);
	
	Boolean userExit(String username);
	
	Boolean emailExit(String email);
	
	List<Account> saveAllAccounts(List<Account> listAccounts);
}
