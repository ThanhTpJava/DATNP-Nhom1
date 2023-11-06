package com.poly.service;



import com.poly.entity.Voucher;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface VoucherService {
    List<Voucher> getAll();
    List<Voucher> findPaginated(Pageable pageable);

    Voucher findByID(Integer id);
    public Voucher create(Voucher voucher) ;

    public Voucher update(Voucher voucher) ;

    public void delete(Integer id) ;

}