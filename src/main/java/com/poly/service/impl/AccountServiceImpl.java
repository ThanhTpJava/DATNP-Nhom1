package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.poly.dao.AccountDAO;
import com.poly.entity.Account;
import com.poly.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	AccountDAO dao;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public List<Account> findAll() {
		return dao.findAll();
	}

	public Account findById(String username) {
		return dao.findById(username).get();
	}

	public List<Account> getAdministrators() {
		return dao.getAdministrators();
	}

	@Override
	public Account createAccount(Account acc) {
		// TODO Auto-generated method stub
		acc.setPassword(passwordEncoder.encode(acc.getPassword()));
		return dao.save(acc);
	}

	@Override
	public Boolean userExit(String username) {
		// TODO Auto-generated method stub
		return dao.existsById(username);
	}

	@Override
	public Boolean emailExit(String email) {
		// TODO Auto-generated method stub
		return dao.exitByEmail(email).isPresent();
	}
}
