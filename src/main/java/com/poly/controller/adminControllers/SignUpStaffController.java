package com.poly.controller.adminControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dto.AccountAdminDTO;
import com.poly.entity.Account;
import com.poly.entity.Authority;
import com.poly.entity.Role;
import com.poly.service.AccountService;
import com.poly.service.AuthorityService;
import com.poly.service.RoleService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class SignUpStaffController {
	private AccountService accountService;
	private AuthorityService authorityService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	AuthorityService authorityService2;
	
	@ModelAttribute("accountdto")
	public AccountAdminDTO accountRegisterDto() {
		return new AccountAdminDTO();
	}
	
	@GetMapping("/admin/signup")
	public String SignUp(Model model) {
		return "admin/SignUpNV";
	}
	
	@PostMapping("/create")
	public String registerAccount(@ModelAttribute("accountdto") AccountAdminDTO accountDto, Model model) {
		if(accountService.checkByEmail(accountDto.getEmail())) {
			return "redirect:/admin/signup?emailexist";
		}
		if(accountDto.getPassword().equals(accountDto.getCheckPass())==false) {
			return "redirect:/admin/signup?checkpass";
		}
		accountService.save(accountDto);
		Role role = roleService.findRole("roleid");	
		Account account = accountService.findById(accountDto.getUsername());
		Authority userAuthorities = new Authority();
		userAuthorities.setAccount(account);
		userAuthorities.setRole(role);
		authorityService2.create(userAuthorities);
		return "redirect:/admin/signup?success";
	}
}
