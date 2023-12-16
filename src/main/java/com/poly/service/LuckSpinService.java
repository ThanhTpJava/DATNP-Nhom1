package com.poly.service;

import com.poly.entity.BlogPost;
import com.poly.entity.LuckySpin;

import java.util.List;

public interface LuckSpinService {

    List<LuckySpin> luckySpinGetAll();

    LuckySpin createSpin (LuckySpin luckySpin);

    LuckySpin updateSpin (LuckySpin luckySpin);

    void deleteSpin(Integer id);
}
