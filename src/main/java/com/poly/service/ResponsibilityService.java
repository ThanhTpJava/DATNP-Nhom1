package com.poly.service;

import com.poly.entity.Account;
import com.poly.entity.Order;
import com.poly.entity.Role;

public interface ResponsibilityService {
	public void saveResponsibility(Account username, Order orderId, Role roleId);
	
	Boolean exitResponsibilityByUsernameAndOrderId(String username, String orderId);
	
	void deleteResponsibility(String username, String orderId);
}
