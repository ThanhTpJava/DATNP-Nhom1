package com.poly.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.poly.entity.Account;
import com.poly.entity.Authority;
import com.poly.entity.Role;

import jakarta.annotation.PostConstruct;

@Service
public class DataInitializer implements CommandLineRunner{

	@Autowired
	RoleService roleService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	AuthorityService authorityService;
	
	private boolean initialized = false;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Role roleCheck = roleService.findRole("ROLE_ADMIN");
		
		if(roleCheck == null) {
			Role roleAdmin = new Role();
			roleAdmin.setId("ROLE_ADMIN");
			roleAdmin.setName("Admin");		
			//roleService.createRole(roleAdmin);
			
			Role roleSale = new Role();
			roleSale.setId("ROLE_SALE");
			roleSale.setName("Sale");			
			//roleService.createRole(roleSale);
			
			Role roleStock = new Role();
			roleStock.setId("ROLE_STOCK");
			roleStock.setName("Stock");	
			//roleService.createRole(roleStock);
			
			Role roleUser = new Role();
			roleUser.setId("ROLE_USER");
			roleUser.setName("User");
			//roleService.createRole(roleUser);
			
			roleService.saveAllRole(Arrays.asList(roleAdmin, roleSale, roleStock, roleUser));
			
			Account accAdmin = new Account();
			accAdmin.setUsername("admin1");
			accAdmin.setPassword("admin1");
			accAdmin.setEmail("admin1@gmail.com");
			accAdmin.setSurname("admin");
			accAdmin.setName("1");
			
			Account accSale = new Account();
			accSale.setUsername("sale1");
			accSale.setPassword("sale1");
			accSale.setEmail("sale1@gmail.com");
			accSale.setSurname("sale");
			accSale.setName("1");
			
			Account accStock = new Account();
			accStock.setUsername("stock1");
			accStock.setPassword("stock1");
			accStock.setEmail("stock1@gmail.com");
			accStock.setSurname("stock1");
			accStock.setName("1");
			
			Account accUser = new Account();
			accUser.setUsername("user1");
			accUser.setPassword("user1");
			accUser.setEmail("user1@gmail.com");
			accUser.setSurname("user1");
			accUser.setName("1");
			
			accountService.saveAllAccounts(Arrays.asList(accAdmin, accSale, accStock, accUser));
			
			Authority authorityAdmin = new Authority();
			authorityAdmin.setRole(roleService.findRole("ROLE_ADMIN"));
			authorityAdmin.setAccount(accountService.findById("admin1"));
			
			Authority authoritySale = new Authority();
			authoritySale.setRole(roleService.findRole("ROLE_SALE"));
			authoritySale.setAccount(accountService.findById("sale1"));
			
			Authority authorityStock = new Authority();
			authorityStock.setRole(roleService.findRole("ROLE_STOCK"));
			authorityStock.setAccount(accountService.findById("stock1"));
			
			Authority authorityUser = new Authority();
			authorityUser.setRole(roleService.findRole("ROLE_USER"));
			authorityUser.setAccount(accountService.findById("user1"));
			
			authorityService.saveAllAuthorities(Arrays.asList(authorityAdmin,
					authoritySale, authorityStock, authorityUser));
			
			initialized = true;
		}
	}

}
