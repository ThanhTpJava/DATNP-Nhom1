package com.poly.service;

import java.util.List;
import java.util.Map;

import com.poly.dto.AccountAdminDTO;
import com.poly.entity.Account;
import com.poly.entity.Authority;

public interface AuthorityService {
	public List<Authority> findAll() ;

	public Authority create(Authority auth) ;

	public void delete(Integer id) ;

	public List<Authority> findAuthoritiesOfAdministrators() ;
	
	List<Authority> saveAllAuthorities(List<Authority> listAuthorities);
	
	Map<String, Boolean> rolesCheck(String username);
	
	String createRoleForUser(String username, String roleId);
	
	String deteteRoleUser(String username, String roleId);
}
