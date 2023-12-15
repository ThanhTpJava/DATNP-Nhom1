package com.poly.dao;

import com.poly.entity.Voucher;
import com.poly.entity.VoucherAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VoucherAccountDao extends JpaRepository<VoucherAccount, Integer> {
    @Query("select va.voucher from VoucherAccount va where va.account.username like %:username%")
    List<Voucher> findVoucherByUser(@Param("username") String username);


}

