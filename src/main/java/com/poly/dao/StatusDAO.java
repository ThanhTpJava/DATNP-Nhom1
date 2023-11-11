package com.poly.dao;

import com.poly.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusDAO extends JpaRepository<Status, Integer> {

}
