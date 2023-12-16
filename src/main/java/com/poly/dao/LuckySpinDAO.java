package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.entity.LuckySpin;

@Repository
public interface LuckySpinDAO extends JpaRepository<LuckySpin, Integer>{

}
