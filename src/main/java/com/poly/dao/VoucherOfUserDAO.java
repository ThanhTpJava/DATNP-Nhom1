package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.poly.entity.VoucherOfUser;

@Repository
public interface VoucherOfUserDAO extends JpaRepository<VoucherOfUser, Integer>{
	
	@Query("SELECT v FROM VoucherOfUser v ORDER BY v.receivedDate DESC")
    List<VoucherOfUser> findAllListVoucherOrUserReceivedDateDesc();
	
	
	@Query("select vou from VoucherOfUser vou where vou.username.username like %:username%")
    List<VoucherOfUser> findVoucherByUser(@Param("username") String username);
	
	
    
}
