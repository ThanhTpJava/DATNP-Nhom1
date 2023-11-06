package com.poly.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.AccountDAO;
import com.poly.dao.AuthorityDAO;
import com.poly.entity.Account;
import com.poly.entity.Authority;
import com.poly.service.AuthorityService;



@Service
public class AuthorityServiceImpl implements AuthorityService{
	@Autowired
	AuthorityDAO dao;
	@Autowired
	AccountDAO accountDAO;

	public List<Authority> findAll() {
		return dao.findAll();
	}

	public Authority create(Authority auth) {
		return dao.save(auth);
	}

	public void delete(Integer id) {
		dao.deleteById(id);
	}

	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Account> accounts = accountDAO.getAdministrators();
		return dao.authoritiesOf(accounts);
	}

	@Override
	public List<Authority> saveAllAuthorities(List<Authority> listAuthorities) {
		// TODO Auto-generated method stub
		return dao.saveAll(listAuthorities);
	}

	@Override
	public Map<String, Boolean> rolesCheck(String username) {
		// TODO Auto-generated method stub
		List<String> roleList = new ArrayList<>();
		roleList.add("ROLE_ADMIN");
		roleList.add("ROLE_STOCK");
		roleList.add("ROLE_SALE");
		
		Map<String, Boolean> MapRolesCheck = new HashMap<>();
//		Authority auth;
		
		for (String role : roleList) {
			MapRolesCheck.put(role, dao.existsByAccountUsernameAndRole(username, role));
		}
		
		return MapRolesCheck;
	}

	@Override
	public String createRoleForUser(String username, String roleId) {
		// TODO Auto-generated method stub
		 dao.addAuthority(username, roleId);
		return "add Authority " + roleId +" for " + username +" success";
	}

	@Override
	public String deteteRoleUser(String username, String roleId) {
		// TODO Auto-generated method stub
		dao.removeAuthority(username, roleId);
		return "delete Authority " + roleId +" for " + username +" success";
	}
}
