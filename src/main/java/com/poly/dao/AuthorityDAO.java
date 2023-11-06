package com.poly.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.poly.entity.Account;
import com.poly.entity.Authority;

@Repository
public interface AuthorityDAO extends JpaRepository<Authority, Integer>{
	@Query("SELECT DISTINCT a FROM Authority a WHERE a.account IN ?1")
	List<Authority> authoritiesOf(List<Account> accounts);
	
	@Query("SELECT auth FROM Authority auth WHERE auth.account.username = ?1 AND auth.role.Id = ?2")
	Authority fidnAuthorityByUsernameAndRoleId(String username, String roleId);
	
	@Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Authority a WHERE a.account.username = :username AND a.role.id = :roleId")
	boolean existsByAccountUsernameAndRole(@Param("username") String username, @Param("roleId") String roleId);

	@Modifying
	@Query("INSERT INTO Authority (account, role) SELECT a, r FROM Account a, Role r WHERE a.username = :username AND r.Id = :roleId")
	void addAuthority(@Param("username") String username, @Param("roleId") String roleId);
	
	@Modifying
	@Query("DELETE FROM Authority WHERE account.username = :username AND role.Id = :roleId")
	void removeAuthority(@Param("username") String username, @Param("roleId") String roleId);
}
