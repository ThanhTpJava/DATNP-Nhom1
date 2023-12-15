package com.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dto.LuckySpinDTO;
import com.poly.service.LuckySpinService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/luckyspin")

public class LuckySpinRestController {
	@Autowired
	LuckySpinService luckySpinService;
	
	@GetMapping("/getvoucher")
	public ResponseEntity<List<LuckySpinDTO>> getAllVoucherForLuckySpin(){
		return ResponseEntity.ok(luckySpinService.getAllVoucherInLuckySpin());
	}
}
