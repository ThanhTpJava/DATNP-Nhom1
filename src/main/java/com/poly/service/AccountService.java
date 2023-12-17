package com.poly.service;

import java.util.List;
import java.util.Optional;

import com.poly.dto.AccountDTO;
import com.poly.entity.Account;

public interface AccountService {
	public List<Account> findAll() ;
	public Account findById(String username) ;
	public List<Account> getAdministrators() ;
	
	List<Account> findByRole(String roleName);
	 
	List<AccountDTO> findAllAccountDTO();
	
	List<AccountDTO> findAccountDTOByRole(String roleName);
	
	AccountDTO getDetailAccountDTO(String username);
	
	Account createAccount(Account acc);
	
	Boolean userExit(String username);
	
	Boolean emailExit(String email);
	
	List<Account> saveAllAccounts(List<Account> listAccounts);
	
	void updateAccount(AccountDTO accDTO);
	
	 Account findByEmail(String email);
	 
	 void updatePasswordByUsername(String password, String username);

	 Account update(Account account);
}
