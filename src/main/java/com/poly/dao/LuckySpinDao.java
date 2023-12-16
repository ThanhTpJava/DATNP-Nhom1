package com.poly.dao;

import com.poly.entity.LuckySpin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LuckySpinDao extends JpaRepository<LuckySpin, Integer> {

}
