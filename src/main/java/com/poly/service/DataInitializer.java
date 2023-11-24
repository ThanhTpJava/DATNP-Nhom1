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
		
		
		if(roleService.exitById("ROLE_ADMIN") == false) {
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

			Role roleShip = new Role();
			roleShip.setId("ROLE_SHIP");
			roleShip.setName("Ship");
			//roleService.createRole(roleUser);
			
			roleService.saveAllRole(Arrays.asList(roleAdmin, roleSale, roleStock, roleUser, roleShip));
			
			Account accAdmin = new Account();
			accAdmin.setUsername("admin1");
			accAdmin.setPassword("admin1");
			accAdmin.setEmail("admin1@gmail.com");
			accAdmin.setSurname("Elon");
			accAdmin.setName("Musk");
			
			Account accSale = new Account();
			accSale.setUsername("sale1");
			accSale.setPassword("sale1");
			accSale.setEmail("sale1@gmail.com");
			accSale.setSurname("Phạm Đình Chí");
			accSale.setName("Kiên");
			
			Account accStock = new Account();
			accStock.setUsername("stock1");
			accStock.setPassword("stock1");
			accStock.setEmail("stock1@gmail.com");
			accStock.setSurname("Nguyễn Quang");
			accStock.setName("Mạnh");
			
			Account accUser = new Account();
			accUser.setUsername("user1");
			accUser.setPassword("user1");
			accUser.setEmail("user1@gmail.com");
			accUser.setSurname("Nguyễn Thị");
			accUser.setName("Trâm");

			Account accShip = new Account();
			accShip.setUsername("ship1");
			accShip.setPassword("ship1");
			accShip.setEmail("ship1@gmail.com");
			accShip.setSurname("Bùi Phúc");
			accShip.setName("Hưng");
			accountService.saveAllAccounts(Arrays.asList(accAdmin, accSale, accStock, accUser, accShip));
			
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

			Authority authorityShip = new Authority();
			authorityShip.setRole(roleService.findRole("ROLE_SHIP"));
			authorityShip.setAccount(accountService.findById("ship1"));

			authorityService.saveAllAuthorities(Arrays.asList(authorityAdmin,
					authoritySale, authorityStock, authorityUser, authorityShip));
			
			initialized = true;
		}
	}

}
