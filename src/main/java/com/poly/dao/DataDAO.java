package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.entity.Data;

@Repository
public interface DataDAO extends JpaRepository<Data, Integer>{

}

