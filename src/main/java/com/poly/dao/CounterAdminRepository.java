package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.entity.Account;
import com.poly.entity.CountAdmin;
import com.poly.entity.Counter;

@Repository
public interface CounterAdminRepository extends JpaRepository<CountAdmin, Integer>{

}
