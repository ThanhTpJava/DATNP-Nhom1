package com.poly.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.poly.dto.AccountDTO;
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
	
	@Modifying
    @Transactional
    @Query("UPDATE Account a SET " +
           "a.name = :#{#dto.name}, " +
           "a.surname = :#{#dto.surname}, " +
           "a.hometown = :#{#dto.hometown}, " +
           "a.residential_address = :#{#dto.residentialAddress}, " +
           "a.delivery_address = :#{#dto.deliveryAddress}, " +
           "a.idCard = :#{#dto.idCard}, " +
           "a.dateOfBirth = :#{#dto.dateOfBirth}, " +
           "a.Gender = :#{#dto.gender}, " +
           "a.email = :#{#dto.email}, " +
           "a.phonenumber = :#{#dto.phoneNumber} " +        
           "WHERE a.username = :#{#dto.username}")
    void updateAccountFromDTO(@Param("dto") AccountDTO dto);

	Optional<Account> findByEmail(String email);
	
	Account findAccountByEmail(String email);
	Account getByEmail(String email);

	@Modifying
	@Transactional
	@Query("UPDATE Account acc SET acc.password = ?1 WHERE acc.username = ?2")
	void updatePasswordByUsername(String password, String username);

	@Query("SELECT acc.spinCount FROM Account acc WHERE acc.username = ?1 ")
    Integer getSpinCount(String username);
}
