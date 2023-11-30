package com.poly.dao;

import com.poly.entity.Account;
import com.poly.entity.Responsibility;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ResponsibilityDAO extends JpaRepository<Responsibility,Long>{
	
	@Query("SELECT res FROM Responsibility res WHERE res.account.username = ?1 AND res.orderResponsi.id = ?2")
	Optional<Responsibility> exitResponsibilityByUsernameAndOrderId(String username, String orderId);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Responsibility res WHERE res.account.username = ?1 AND res.orderResponsi.id = ?2")
	void deleteResponsibility(String username, String orderId);

}
