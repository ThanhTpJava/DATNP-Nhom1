package com.poly.dao;


import com.poly.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VoucherDAO extends JpaRepository<Voucher, String>{
//    @Query("SELECT v FROM Voucher v WHERE LOWER(v.code_voucher) LIKE %:code%")
//    Voucher getIdVoucher(@Param("code") String code);
	
	@Modifying
    @Transactional
    @Query("UPDATE Voucher v SET v.quantity = (SELECT v.quantity FROM Voucher v WHERE v.voucherCode = :vcode) - 1 WHERE v.voucherCode = :vcode")
    void updateVoucherQuantity(@Param("vcode") String vcode);
}
