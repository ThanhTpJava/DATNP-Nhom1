package com.poly.controller.adminControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class VoucherController {
    @GetMapping("admin/voucher")
    public String getAll(){
        return"admin/voucher";
    }

    @GetMapping("admin/luckySpin")
    public String getToAddLuckySpin(){
        return"admin/luckySpin";
    }


}
