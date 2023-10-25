package com.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.RoleDAO;
import com.poly.entity.Role;
import com.poly.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleDAO dao;

	public List<Role> findAll() {
		return dao.findAll();
	}

	@Override
	public Role findRole(String roleID) {
		// TODO Auto-generated method stub
		return dao.findById(roleID).get();
	}

	@Override
	public Role createRole(Role role) {
		// TODO Auto-generated method stub
		return dao.save(role);
	}

	@Override
	public List<Role> saveAllRole(List<Role> listRoles) {
		// TODO Auto-generated method stub
		return dao.saveAll(listRoles);
	}

	@Override
	public Boolean exitById(String roleId) {
		// TODO Auto-generated method stub
		return dao.existsById(roleId);
	}
	
	
}
