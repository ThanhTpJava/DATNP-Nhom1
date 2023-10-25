package com.poly.service;

import java.util.List;
import java.util.Optional;

import com.poly.entity.Role;

public interface RoleService {
	public List<Role> findAll() ;
	Role findRole(String roleID);
	
	Role createRole(Role role);
	
	List<Role> saveAllRole(List<Role> listRoles);
	
	Boolean exitById(String roleId);
}
