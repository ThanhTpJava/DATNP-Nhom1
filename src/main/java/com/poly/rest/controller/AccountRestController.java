package com.poly.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.poly.entity.Account;
import com.poly.entity.Authority;
import com.poly.entity.Role;
import com.poly.service.AccountService;
import com.poly.service.AuthorityService;
import com.poly.service.RoleService;

import jakarta.validation.Valid;



@CrossOrigin("*")
@RestController
//@RequestMapping("/rest/accounts")
@RequestMapping("/auth")
public class AccountRestController {
	@Autowired
	AccountService accountService;
	
	@Autowired
	AuthorityService auService;
	
	@Autowired
	RoleService roleService;
	@GetMapping
	public List<Account> getAccounts(@RequestParam("admin") Optional<Boolean> admin) {
		if(admin.orElse(false)) {
			return accountService.getAdministrators();
		}
		return accountService.findAll();
	}
	
	@GetMapping("{username}")
	public Account getOne(@PathVariable("username") String username) {
		return accountService.findById(username);
	}
	
	@PostMapping("/signup/newaccount")
	public ResponseEntity<Account> createAccount(@RequestBody @Valid Account acc){
		if(accountService.userExit(acc.getUsername()) || accountService.emailExit(acc.getEmail())) {
			return ResponseEntity.badRequest().build();
		}
		
		Account createAccount = accountService.createAccount(acc);
		
		Role userRole = roleService.findRole("ROLE_USER");
		
		Authority userAuthorities = new Authority();
		userAuthorities.setAccount(createAccount);
		userAuthorities.setRole(userRole);
		auService.create(userAuthorities);
		
		return new ResponseEntity<>(createAccount, HttpStatus.CREATED);
	}
	
	@GetMapping("/checkusernameexits/{username}")
	public Map<String, Boolean> checkUsernameAvailability(@PathVariable("username") String username) {
        Map<String, Boolean> response = new HashMap<>();
        
        // Kiểm tra xem tên người dùng đã tồn tại trong cơ sở dữ liệu hay chưa
        boolean isAvailable = !accountService.userExit(username);

        response.put("available", isAvailable);

        return response;
    }
	
	@GetMapping("/checkemailexits/{email}")
	public Map<String, Boolean> checkEmailAvailability(@PathVariable("email") String email) {
        Map<String, Boolean> response = new HashMap<>();       
       
        boolean isAvailable = !accountService.emailExit(email);
        response.put("available", isAvailable);
        return response;
    }
}
