package com.poly.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.poly.entity.Account;

@Repository
public interface AccountDAO extends JpaRepository<Account, String>{
	@Query("SELECT DISTINCT ar.account  FROM Authority ar WHERE ar.role.Id IN ('admin')")
	List<Account> getAdministrators();
	
	@Query("SELECT acc FROM Account acc WHERE acc.email = ?1")
	Optional<Account> exitByEmail(String email);
	
	@Query("SELECT acc FROM Account acc WHERE acc.username = ?1")
	Optional<Account> findByUsername(String username);

	Account findAccountsByUsername(String Username);
}
