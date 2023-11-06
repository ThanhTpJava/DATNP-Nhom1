package com.poly.rest.controller;

import java.awt.Choice;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dto.AccountDTO;
import com.poly.service.AccountService;
import com.poly.service.AuthorityService;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/accounts")
public class AdminAccountRestController {
	
	@Autowired
	AccountService accService;
	
	@Autowired
	AuthorityService authService;
	
	@GetMapping("/detail/{username}")
	public ResponseEntity<AccountDTO> getDetailAccount(@PathVariable String username){
		return ResponseEntity.ok(accService.getDetailAccountDTO(username));
	}
	
	@Transactional
	@PostMapping("/updaterole")
	public ResponseEntity<?> updateRoles(@RequestBody Map<String, Object> requestData) {
		String username = (String) requestData.get("username");
        Map<String, Boolean> roles = (Map<String, Boolean>) requestData.get("roles");

//        System.out.println(username);
//      
//        System.out.println("JSON Data: ");
//        for (Map.Entry<String, Boolean> entry : roles.entrySet()) {
//        	
//            String roleName = entry.getKey();
//            Boolean roleValue = entry.getValue();
//            System.out.println("Role: " + roleName + ", Value: " + roleValue);
//            
//        }
        System.out.println("-------------------------------");
        Map<String, Boolean> mapRoleCheck =
        authService.rolesCheck(username);
        
//        System.out.println("Map Data Role check : ");
//        for (Map.Entry<String, Boolean> rolec : mapRoleCheck.entrySet()) {
//        	String roleName = rolec.getKey();
//            Boolean roleValue = rolec.getValue();
//            System.out.println("Role: " + roleName + ", Value: " + roleValue);
//		}
        
        for (Map.Entry<String, Boolean> entry : roles.entrySet()) {
            String roleName = entry.getKey();
            Boolean roleValue = entry.getValue();
            
            // Lấy giá trị từ Map Data Role check
            Boolean mapRoleCheckValue = mapRoleCheck.get(roleName);
            
            if(mapRoleCheckValue != roleValue && roleValue == true) {
            	System.out.println("Thêm quyền " + roleName + " cho tài khoản " + username);
            	String addAuth = authService.createRoleForUser(username, roleName);
            	System.out.println(addAuth);
            }
            
            if(mapRoleCheckValue != roleValue && roleValue == false) {
            	System.out.println("Xóa quyền " + roleName + " cho tài khoản " + username);
            	String deleteAuth = authService.deteteRoleUser(username, roleName);
            	System.out.println(deleteAuth);
            }
        }
        
        return new ResponseEntity<String>("{\"message\":\"success\"}", HttpStatus.OK); 
    }
}
