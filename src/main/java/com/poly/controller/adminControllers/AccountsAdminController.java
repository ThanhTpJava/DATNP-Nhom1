package com.poly.controller.adminControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dto.AccountDTO;
import com.poly.service.AccountService;

@Controller
public class AccountsAdminController {
	
	@Autowired
	AccountService accService;
	
	 @RequestMapping("/admin/accounts")

	    public String Account(Model model, 
	    		@RequestParam(name = "role", required = false, defaultValue = "All") String role) {
	    	
	    	List<AccountDTO> lisAccountDTOs;
	    	
	    	if("All".equalsIgnoreCase(role)) {
	    		lisAccountDTOs = accService.findAllAccountDTO();
	    	}else {
	    		lisAccountDTOs = accService.findAccountDTOByRole(role);
	    		if (lisAccountDTOs.isEmpty()) {
					System.out.println("Rỗng");
				}
			}
	    	
	    	model.addAttribute("listAccounts", lisAccountDTOs);
	    	
	        return "admin/accounts";

	    }
}
