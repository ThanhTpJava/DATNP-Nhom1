package com.poly.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.AccountDAO;
import com.poly.dao.AuthorityDAO;
import com.poly.dao.RoleDAO;
import com.poly.entity.Authority;
import com.poly.service.AuthorityService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/authorities")
public class AuthorityRestController {
	@Autowired AuthorityDAO authorityDAO;
	@Autowired RoleDAO roleDAO;
	@Autowired AccountDAO accountDAO;
	
	@GetMapping("/rest/authorities")
	public Map<String, Object> getAuthorities(){
		Map<String, Object> data = new HashMap<>();
		data.put("authorities", authorityDAO.findAll());
		System.out.println(authorityDAO.findAll());
		data.put("accounts", accountDAO.findAll());
		data.put("roles", roleDAO.findAll());
		return data;
		
	}
	
	@PostMapping("/rest/authorities")
	public Authority create(@RequestBody Authority authority) {
		return authorityDAO.save(authority);
	}
	
	@DeleteMapping("/rest/authorities/{id}")
	public void delete2(@PathVariable Integer id) {
	    	System.out.println(id);
	        authorityDAO.deleteById(id);
	}
	
	
	@Autowired
	AuthorityService authorityService;
	
	@GetMapping
	public List<Authority> findAll(@RequestParam("admin") Optional<Boolean> admin) {
		if(admin.orElse(false)) {
			return authorityService.findAuthoritiesOfAdministrators();
		}
		return authorityService.findAll();
	}
	
	@PostMapping
	public Authority post(@RequestBody Authority auth) {
		return authorityService.create(auth);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		authorityService.delete(id);
	}
}
