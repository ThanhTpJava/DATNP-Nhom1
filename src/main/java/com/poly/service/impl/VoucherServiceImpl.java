package com.poly.service.impl;

import com.poly.dao.VoucherDAO;
import com.poly.entity.Voucher;
import com.poly.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    VoucherDAO voucherDAO;

    @Override
    public List<Voucher> getAll() {
        return voucherDAO.findAll();
    }

    @Override
    public List<Voucher> findPaginated(Pageable pageable) {
        Page<Voucher> voucherPage = voucherDAO.findAll(pageable);
        return voucherPage.getContent();
    }

    @Override
    public Voucher findByID(Integer id) {
        return voucherDAO.findById(id).get();
    }

    @Override
    public Voucher create(Voucher voucher) {
       return voucherDAO.save(voucher);
    }

    @Override
    public Voucher update(Voucher voucher) {
        return voucherDAO.save(voucher);
    }

    @Override
    public void delete(Integer id) {
        voucherDAO.deleteById(id);
    }
}
