package com.poly.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	@Query("SELECT acc FROM Account acc JOIN Authority auth ON acc.username = auth.account.username "
			+ "WHERE auth.role.Id = :roleName")
    List<Account> findAccountsByRole(@Param("roleName") String roleName);
//
//	@Query("SELECT NEW AccountDTO(a.username, a.name, a.surname, a.hometown, a.residentialAddress,"
//			+ " a.deliveryAddress, a.idCard, a.dateOfBirth, a.gender, a.email, a.phoneNumber,"
//			+ " auth.role.Id) FROM Account a JOIN a.authorities auth")
//    List<AccountDTO> findAllAccountsAndRoles();
//
//	@Query("SELECT NEW AccountDTO"
//			+ "(a.username, a.name, a.surname, a.hometown, a.residentialAddress, a.deliveryAddress,"
//			+ " a.idCard, a.dateOfBirth, a.gender, a.email, a.phoneNumber, auth.role.Id) "
//			+ "FROM Account a JOIN a.authorities auth ON a.username = auth.account.username "
//			+ "WHERE auth.role.Id = :roleName")
//    List<AccountDTO> findAccountsAndRolesByRole(@Param("roleName") String roleName);



}
