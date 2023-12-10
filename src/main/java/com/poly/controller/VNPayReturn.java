package com.poly.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.weaving.DefaultContextLoadTimeWeaver;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.poly.dto.AccountDTO;
import com.poly.dto.OrderPaymentDTO;
import com.poly.dto.orderDetails;
import com.poly.entity.Account;
import com.poly.entity.Order;
import com.poly.entity.OrderDetail;
import com.poly.entity.OrderStatus;
import com.poly.entity.Product;
import com.poly.entity.Status;
import com.poly.service.AccountService;
import com.poly.service.OrderDetailService;
import com.poly.service.OrderService;
import com.poly.service.OrderStatusService;
import com.poly.service.ProductService;
import com.poly.service.StatusService;

import jakarta.servlet.http.HttpSession;

@Controller
@Component
public class VNPayReturn {
	

	@Autowired
	orderDetails orderDetails;

	@Autowired
	AccountService accountService;

	@Autowired
	ProductService productService;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderDetailService orderDetailService;

	@Autowired
	StatusService statusService;
	
	@Autowired
	OrderStatusService orderStatusService;

	@RequestMapping("/api/vnpay/return")
	public String vnpayReturn(Model model,
			HttpSession session,
			@RequestParam("vnp_Amount") String vnpAmount,
			@RequestParam("vnp_BankCode") String vnpBankCode,
			@RequestParam(value = "vnp_BankTranNo", required = false) String vnpBankTranNo,
			@RequestParam("vnp_CardType") String vnpCardType, @RequestParam("vnp_OrderInfo") String vnpOrderInfo,
			@RequestParam("vnp_PayDate") String vnpPayDate, @RequestParam("vnp_ResponseCode") String vnpResponseCode,
			@RequestParam("vnp_TmnCode") String vnpTmnCode, @RequestParam("vnp_TransactionNo") String vnpTransactionNo,
			@RequestParam("vnp_TransactionStatus") String vnpTransactionStatus,
			@RequestParam("vnp_TxnRef") String vnpTxnRef, @RequestParam("vnp_SecureHash") String vnpSecureHash) {

		if (vnpResponseCode.equals("00") && vnpTransactionStatus.equals("00")) {
			 OrderPaymentDTO orderPaymentDTO = (OrderPaymentDTO) session.getAttribute("orderPaymentDTO");
			
			Account account = accountService.findById(orderPaymentDTO.getAccount().getUsername());

			// Tạo hóa đơn
			Order order = new Order();
			order.setId(orderPaymentDTO.getId());
			order.setAccount(account);
			order.setAddress(orderPaymentDTO.getAddress());
			order.setCreateDate(orderPaymentDTO.getCreateDate());
			order.setDelivery_phone(orderPaymentDTO.getDelivery_phone());
			order.setTotalAmount(orderPaymentDTO.getTotalAmount());
			orderService.createOrderPaymentApi(order);

			List<orderDetails> orderdetailDTO = orderPaymentDTO.getOrderDetails();

			// Tạo hóa đơn chi tiết
			List<OrderDetail> listOrderDetails = new ArrayList<>();

			for (orderDetails odDTO : orderdetailDTO) {
				OrderDetail ordDetail = new OrderDetail();
				Product prd = new Product();
				prd = productService.findByID(odDTO.getProduct().getId());
				ordDetail.setOrder(order);
				ordDetail.setProduct(prd);
				ordDetail.setQuantity(odDTO.getQuantity());
				ordDetail.setSubtotal(odDTO.getSubtotal());

				listOrderDetails.add(ordDetail);
				
				
			}

			orderDetailService.createOrderDetailPaymentApi(listOrderDetails);

			// Tạo trạng thái cho hóa đơn
			int statusId = 2;
			Status findSttData = statusService.findStatusById(statusId);

			OrderStatus orderStatus = new OrderStatus();
			orderStatus.setOrder(order);
			orderStatus.setStatus(findSttData);

			orderStatusService.saveOrderStatus(orderStatus);
			
			//Hiển thị ở trang thanh toán thành công
			String inputString = vnpPayDate;
			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

			// Chuyển đổi chuỗi vào đối tượng LocalDateTime
			LocalDateTime dateTime = LocalDateTime.parse(inputString, inputFormatter);

			// Định dạng lại thành chuỗi mong muốn
			DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			String outputString = dateTime.format(outputFormatter);
			model.addAttribute("transactionTime", outputString);
			model.addAttribute("orderId", orderPaymentDTO.getId());
			return "user/payment-api-return";
		} else {
			return "redirect:https://sandbox.vnpayment.vn/paymentv2/Payment/Error.html?code=" + vnpResponseCode;
		}

	}
}
