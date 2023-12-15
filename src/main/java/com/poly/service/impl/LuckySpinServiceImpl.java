package com.poly.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.LuckySpinDAO;
import com.poly.dto.LuckySpinDTO;
import com.poly.dto.LuckySpinDTOMapper;
import com.poly.entity.LuckySpin;
import com.poly.service.LuckySpinService;

@Service
public class LuckySpinServiceImpl implements LuckySpinService{
	
	@Autowired
	LuckySpinDAO luckySpinDAO;
	
	@Autowired
	LuckySpinDTOMapper luckySpinDTOMapper;
	@Override
	public List<LuckySpinDTO> getAllVoucherInLuckySpin() {
		// TODO Auto-generated method stub
		return luckySpinDAO.findAll().stream().map(luckySpinDTOMapper).collect(Collectors.toList());
	}

}
