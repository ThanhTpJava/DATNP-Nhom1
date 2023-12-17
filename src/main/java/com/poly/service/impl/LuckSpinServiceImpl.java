package com.poly.service.impl;

import com.poly.dao.LuckySpinDAO;
import com.poly.dto.LuckySpinDTO;
import com.poly.dto.LuckySpinDTOMapper;
import com.poly.entity.BlogPost;
import com.poly.entity.LuckySpin;
import com.poly.service.LuckSpinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LuckSpinServiceImpl implements LuckSpinService {

    @Autowired
    LuckySpinDAO dao;

    @Autowired
    LuckySpinDTOMapper mapper;

    @Override
    public List<LuckySpinDTO> SpinGetAll() {
        return  dao.findAll().stream().map(mapper).collect(Collectors.toList());
    }

    @Override
    public List<LuckySpin> luckySpinGetAll() {
        return dao.findAll();
    }

    @Override
    public LuckySpin createSpin(LuckySpin luckySpin) {
        return dao.save(luckySpin);
    }

    @Override
    public LuckySpin updateSpin(LuckySpin luckySpin) {
        return dao.save(luckySpin);
    }

    @Override
    public void deleteSpin(Integer id) {
            dao.deleteById(id);
    }
}
