package com.poly.dao;


import com.poly.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherDAO extends JpaRepository<Voucher, String>{
//    @Query("SELECT v FROM Voucher v WHERE LOWER(v.code_voucher) LIKE %:code%")
//    Voucher getIdVoucher(@Param("code") String code);

}
