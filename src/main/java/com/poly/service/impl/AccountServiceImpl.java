package com.poly.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.poly.dto.AccountAdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.poly.dao.AccountDAO;
import com.poly.dto.AccountDTO;
import com.poly.dto.AccountDTOMapper;
import com.poly.entity.Account;
import com.poly.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	AccountDAO dao;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AccountDTOMapper accountDTOMapper;
	
	@Override
	    public Account findByEmail(String email) {
	        return dao.findByEmail(email).orElse(null);
	    }
	
	public List<Account> findAll() {
		return dao.findAll();
	}

	public Account findById(String username) {
		return dao.findByUsername(username).get();
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
		return dao.findByUsername(username).isPresent();
	}

	@Override
	public Boolean emailExit(String email) {
		// TODO Auto-generated method stub
		return dao.exitByEmail(email).isPresent();
	}

	@Override
	public List<Account> saveAllAccounts(List<Account> listAccounts) {
		// TODO Auto-generated method stub
		for(Account acc: listAccounts) {
			acc.setPassword(passwordEncoder.encode(acc.getPassword()));
		}
		return dao.saveAll(listAccounts);
	}

	@Override
	public List<Account> findByRole(String roleName) {
		// TODO Auto-generated method stub
		return dao.findAccountsByRole(roleName);
	}

	@Override
	public List<AccountDTO> findAllAccountDTO() {
		// TODO Auto-generated method stub
		return  dao.findAll().stream()
				.map(accountDTOMapper).collect(Collectors.toList());
	}

	@Override
	public List<AccountDTO> findAccountDTOByRole(String roleName) {
		// TODO Auto-generated method stub
		return dao.findAccountsByRole(roleName).stream().map(accountDTOMapper).collect(Collectors.toList());
	}

	@Override
	public AccountDTO getDetailAccountDTO(String username) {
		// TODO Auto-generated method stub
		List<AccountDTO> accountDTOs = dao.findById(username).stream().map(accountDTOMapper).collect(Collectors.toList());
	    // Trả về đối tượng đầu tiên nếu có, hoặc null nếu danh sách rỗng
	    return accountDTOs.isEmpty() ? null : accountDTOs.get(0);
	}

	@Override
	public void updateAccount(AccountDTO accDTO) {
		// TODO Auto-generated method stub
		  dao.updateAccountFromDTO(accDTO);
		 
	}

	@Override
	public void updatePasswordByUsername(String password, String username) {
		// TODO Auto-generated method stub
		String passwordEncode = passwordEncoder.encode(password);
		dao.updatePasswordByUsername(passwordEncode, username);
	}

	@Override
	public Account update(Account account) {
		return dao.save(account);
	}



	@Override
	public void save(AccountAdminDTO accountDto) {
		accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));
		Account account = new Account(
				accountDto.getUsername(),
				accountDto.getName(),
				accountDto.getSurname(),
				accountDto.getPassword(),
				null,
				null,
				null,
				null,
				null,
				accountDto.getGender().UNKNOW,
				accountDto.getEmail(),
				null,
				null,
				null,
				null,
				null,
				null,
				0

		);
		dao.save(account);
	}

	@Override
	public Account getByEmail(String email) {
		// TODO Auto-generated method stub
		return dao.getByEmail(email);
	}

	@Override
	public Boolean checkPasswordAccount(String email, String password) {
		Account account = dao.findAccountByEmail(email);
		if(account.getPassword().equals(password)) return true;
		return false;
	}

	@Override
	public Boolean checkByEmail(String email) {
		Account account = dao.findAccountByEmail(email);
		if(account == null) return false;
		return true;
	}


}
