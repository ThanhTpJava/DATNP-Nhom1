package com.poly.rest.controller;


import com.poly.dto.LuckySpinDTO;
import com.poly.entity.LuckySpin;
import com.poly.entity.Product;
import com.poly.service.LuckSpinService;
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

    @GetMapping()
    public List<LuckySpin> getAll() {
        return luckSpinService.luckySpinGetAll();
    }

    @PostMapping()
    public LuckySpin push(@RequestBody LuckySpin luckySpin) {
        return luckSpinService.createSpin(luckySpin);
    }


//	@GetMapping("/getvoucher")
//	public ResponseEntity<List<LuckySpinDTO>> getAllVoucherForLuckySpin(){
//		return ResponseEntity.ok(luckSpinService.getAllVoucherInLuckySpin());
//	}


}
