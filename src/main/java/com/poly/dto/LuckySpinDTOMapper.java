package com.poly.dto;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.poly.entity.LuckySpin;

@Component
public class LuckySpinDTOMapper implements Function<LuckySpin, LuckySpinDTO>{

	@Override
	public LuckySpinDTO apply(LuckySpin ls) {
		// TODO Auto-generated method stub
		return new LuckySpinDTO(
				ls.getVoucherCode().getVoucherCode(), 
				ls.getVoucherCode().getReview(), 
				ls.getVoucherCode().getDescription(),
				ls.getVoucherCode().getRate()	
			);
	}

}
