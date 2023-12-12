package com.poly.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.poly.dao.AccountDAO;
import com.poly.dao.AuthorityDAO;
import com.poly.dto.AccountDTO;
import com.poly.entity.Account;
import com.poly.entity.Authority;
import com.poly.service.AccountService;
import com.poly.service.AuthorityService;

import jakarta.servlet.http.HttpSession;



@Service
public class AuthorityServiceImpl implements AuthorityService{
	@Autowired
	AuthorityDAO dao;
	@Autowired
	AccountDAO accountDAO;
	@Autowired
	HttpSession session;
	@Autowired
	AccountService accService;
	
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
	
	@Override
	public String getLoginAuthority() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName(); // Lấy tên người dùng
		String role = "";

		if (authentication.isAuthenticated()) {
			// Người dùng đã được xác thực
			switch (authentication.getAuthorities().stream().findFirst().orElse(null).getAuthority()) {
				case "ROLE_ADMIN":
					role = "ROLE_ADMIN";
					break;
				case "ROLE_USER":
					role = "ROLE_USER";
					break;
				case "ROLE_SALE":
					role = "ROLE_SALE";
					break;
				case "ROLE_STOCK":
					role = "ROLE_STOCK";
					break;
				case "ROLE_SHIP":
					role = "ROLE_SHIP";
					break;
				default:
					role = "No specific role";
					break;
			}
			Account account = accountDAO.findAccountsByUsername(username);
			session.setAttribute("authentication", account);

			AccountDTO accountDTO = accService.getDetailAccountDTO(username);
			session.setAttribute("account", accountDTO);

			return role;
		} else {
			// Người dùng chưa xác thực
			return null;
		}
	}
}
