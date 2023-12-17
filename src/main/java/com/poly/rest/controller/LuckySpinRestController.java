package com.poly.rest.controller;

import com.poly.dto.LuckySpinDTO;
import com.poly.entity.Account;
import com.poly.entity.LuckySpin;
import com.poly.entity.Product;
import com.poly.entity.Voucher;
import com.poly.service.AccountService;
import com.poly.service.LuckSpinService;
import com.poly.service.VoucherService;

import jakarta.servlet.http.HttpSession;

import com.poly.service.LuckySpinService;
import com.poly.service.VoucherOfUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/luckySpin")
public class LuckySpinRestController {

	@Autowired
	LuckSpinService luckSpinService;

	@Autowired
	VoucherService voucherService;

	@Autowired
	LuckySpinService luckySpinService;

	@Autowired
	VoucherOfUserService voucherOfUserService;

	@Autowired
	AccountService accountService;

	@Autowired
	HttpSession session;
	
	@GetMapping()
	public List<LuckySpin> getAll() {
		return luckSpinService.luckySpinGetAll();
	}

	@GetMapping("/getAll")
	public List<LuckySpinDTO> getAll2() {
		return luckSpinService.SpinGetAll();
	}

	@PostMapping("/{voucherCode}")
	public LuckySpin push(@PathVariable String voucherCode) {
		Voucher voucher = voucherService.findByID(voucherCode);
		System.out.println(voucher.toString());
		LuckySpin luckySpin = new LuckySpin();
		luckySpin.setVoucherCode(voucher);
		return luckSpinService.createSpin(luckySpin);
	}

	@DeleteMapping("{id}")
	public void DeleteSpin(@PathVariable Integer id) {
		luckSpinService.deleteSpin(id);
	}

//	@GetMapping("/getvoucher")
//	public ResponseEntity<List<LuckySpinDTO>> getAllVoucherForLuckySpin(){
//		return ResponseEntity.ok(luckSpinService.getAllVoucherInLuckySpin());
//	}
	@GetMapping("/getvoucher")
	public ResponseEntity<List<LuckySpinDTO>> getAllVoucherForLuckySpin() {
		return ResponseEntity.ok(luckySpinService.getAllVoucherInLuckySpin());
	}

	@PostMapping("/savevoucherforuser")
	public ResponseEntity<?> saveVoucherForUser(@RequestBody Map<String, Object> voucherMap) {

		String username = (String) voucherMap.get("username");
		String voucherCode = (String) voucherMap.get("voucherCode");
		String receivedDateStr = (String) voucherMap.get("received_date");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date received_date = null;

		try {
			received_date = dateFormat.parse(receivedDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			// Xử lý ngoại lệ nếu có
		}

		voucherOfUserService.saveVoucherForUser(username, voucherCode, received_date, true);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/get/spincount")
	public ResponseEntity<Integer> getSpinCount() {
		Account account = (Account) session.getAttribute("authentication");
		String username = account.getUsername(); 
		try {
			// Gọi phương thức service để lấy số lần quay spin từ cơ sở dữ liệu
			Integer spinCount = accountService.getSpinCount(username);

			// Trả về giá trị thành công và số lần quay spin
			return ResponseEntity.ok(spinCount);
		} catch (Exception e) {
			// Nếu có lỗi, trả về lỗi và thông báo
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	 private final Lock lock = new ReentrantLock();
	
	@PutMapping("/put/minusspincount/{spinCount}")
	public ResponseEntity<Account> minusSpinCount(@PathVariable("spinCount") String spinCount ) {
		
		Account account = (Account) session.getAttribute("authentication");
		try {
			
            int currentCount = Integer.parseInt(spinCount);
            
            System.out.println("currentCount" + currentCount);
            if (currentCount > 0) {
                int updatedCount = currentCount - 1;
                account.setSpinCount(updatedCount);
                // Lưu account vào cơ sở dữ liệu
                return ResponseEntity.ok(accountService.update(account));
            } else {
                // Không đủ lượt quay, xử lý tương ứng
                return ResponseEntity.badRequest().body(null);
            }
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
