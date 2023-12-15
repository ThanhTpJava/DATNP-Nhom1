package com.poly.controller;

import com.poly.dao.VoucherAccountDao;
import com.poly.dao.VoucherDAO;
import com.poly.entity.Account;
import com.poly.entity.Voucher;
import com.poly.entity.VoucherAccount;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class VoucherUserController {


    @Autowired
    HttpSession session;

    @Autowired
    VoucherAccountDao voucherAccountDao;

    @Autowired
    VoucherDAO voucherDAO;
    @GetMapping("/voucher")
    public String findVoucherByUserId(Model model) {
        Account account = (Account) session.getAttribute("authentication");
        String username = account.getUsername();
        System.out.println(username);

        try {
            List<Voucher> VoucherForUser = voucherAccountDao.findVoucherByUser(username);
            System.out.println(VoucherForUser.toString());
            model.addAttribute("listVoucher", VoucherForUser);
            return "user/voucher";
        }catch (NullPointerException nullPointerException){
           throw nullPointerException;

        }

    }

    @PostMapping("/voucher/{code}")
    public String addVoucherToUser(@RequestParam("code") String voucherCode ){
        Account account = (Account) session.getAttribute("authentication");
        Voucher voucher = voucherDAO.getIdVoucher(voucherCode);
        System.out.println(voucherCode);
        System.out.println(voucher.getName());

        VoucherAccount va = new VoucherAccount();
        va.setAccount(account);
        va.setVoucher(voucher);
        va.setStatus(false);
        voucherAccountDao.save(va);
        return "redirect:/voucher";
    }

}
