package com.poly.service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.poly.dao.AccountDAO;
import com.poly.entity.Account;
import com.poly.entity.UserInfoToUserDetail;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{

	private final AccountDAO AccRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Account> accInfo = AccRepo.findByUsername(username);
		Account account = accInfo.get();
		if(account != null) {
			System.out.println(account.getUsername());
			System.out.println(account.getPassword());
		}
		return accInfo.map(UserInfoToUserDetail::new)
				.orElseThrow(() -> new UsernameNotFoundException("user not found : " + username));
	}

}
