package com.poly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.ResponsibilityDAO;
import com.poly.entity.Account;
import com.poly.entity.Order;
import com.poly.entity.Responsibility;
import com.poly.entity.Role;
import com.poly.service.ResponsibilityService;

@Service
public class ResponsibilityServiceImpl implements ResponsibilityService {
	@Autowired
	ResponsibilityDAO responsibilityDAO;

	@Override
	public void saveResponsibility(Account username, Order orderId, Role roleId) {
		// TODO Auto-generated method stub
		boolean checkRes = responsibilityDAO.exitResponsibilityByUsernameAndOrderId(username.getUsername(), orderId.getId()).isPresent();
		Responsibility responsi = new Responsibility();
		if (checkRes == true) {
			responsibilityDAO.deleteResponsibility(username.getUsername(), orderId.getId());
		}

		responsi.setAccount(username);
		responsi.setOrderResponsi(orderId);
		responsi.setRole(roleId);
		responsibilityDAO.save(responsi);
	}

	@Override
	public Boolean exitResponsibilityByUsernameAndOrderId(String username, String orderId) {
		// TODO Auto-generated method stub
		return responsibilityDAO.exitResponsibilityByUsernameAndOrderId(username, orderId).isPresent();
	}

	@Override
	public void deleteResponsibility(String username, String orderId) {
		responsibilityDAO.deleteResponsibility(username, orderId);
	}
}
