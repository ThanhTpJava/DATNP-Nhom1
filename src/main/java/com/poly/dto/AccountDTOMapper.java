package com.poly.dto;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.poly.entity.Account;

@Component
public class AccountDTOMapper implements Function<Account, AccountDTO>{

	@Override
	public AccountDTO apply(Account acc) {
		// TODO Auto-generated method stub
		return new AccountDTO(
				acc.getUsername(), 
				acc.getName(), 
				acc.getSurname(), 
				acc.getHometown(),
				acc.getResidential_address(), 
				acc.getDelivery_address(),
				acc.getIdCard(), 
				acc.getDateOfBirth(), 
				acc.getGender(), 
				acc.getEmail(), 
				acc.getPhonenumber(), 
				acc.getPhoto(),
				acc.getAuthorities().stream()
				.map(role -> role.getRole().getName())
				.collect(Collectors.toList())
				);
	}

}
