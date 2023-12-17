package com.poly.service;

import com.poly.entity.Order;
import com.poly.entity.OrderVoucher;
import com.poly.entity.Voucher;

public interface OrderVoucherService {
	public OrderVoucher saveOrderVoucher(String order, String voucher);
}
