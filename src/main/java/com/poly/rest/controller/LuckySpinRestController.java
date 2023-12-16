package com.poly.rest.controller;


import com.poly.dto.LuckySpinDTO;
import com.poly.entity.LuckySpin;
import com.poly.entity.Product;
import com.poly.entity.Voucher;
import com.poly.service.LuckSpinService;
import com.poly.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/luckySpin")
public class LuckySpinRestController {

    @Autowired
    LuckSpinService luckSpinService;

    @Autowired
    VoucherService voucherService;

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


}
