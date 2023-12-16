package com.poly.service.impl;

import com.poly.dao.LuckySpinDao;
import com.poly.entity.BlogPost;
import com.poly.entity.LuckySpin;
import com.poly.service.LuckSpinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LuckSpinServiceImpl implements LuckSpinService {

    @Autowired
    LuckySpinDao dao;

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

    }
}
