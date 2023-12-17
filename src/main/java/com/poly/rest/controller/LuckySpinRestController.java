package com.poly.rest.controller;


import com.poly.dto.LuckySpinDTO;
import com.poly.entity.LuckySpin;
import com.poly.entity.Product;
import com.poly.entity.Voucher;
import com.poly.service.LuckSpinService;
import com.poly.service.VoucherService;
import com.poly.service.LuckySpinService;
import com.poly.service.VoucherOfUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public void DeleteSpin(@PathVariable Integer id){
        luckSpinService.deleteSpin(id);
    }

//	@GetMapping("/getvoucher")
//	public ResponseEntity<List<LuckySpinDTO>> getAllVoucherForLuckySpin(){
//		return ResponseEntity.ok(luckSpinService.getAllVoucherInLuckySpin());
//	}
	@GetMapping("/getvoucher")
	public ResponseEntity<List<LuckySpinDTO>> getAllVoucherForLuckySpin(){
		return ResponseEntity.ok(luckySpinService.getAllVoucherInLuckySpin());
	}

	@PostMapping("/savevoucherforuser")
	public ResponseEntity<?> saveVoucherForUser(@RequestBody Map<String, Object> voucherMap){

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
}
