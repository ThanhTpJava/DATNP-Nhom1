//package com.poly.rest.controller;
//
//import com.poly.entity.Voucher;
//import com.poly.service.VoucherService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin("*")
//@RequestMapping("/rest/voucher")
//public class VoucherRestController {
//    @Autowired
//    VoucherService voucherService;
//
//    @GetMapping
//    public List<Voucher> getAll() {
//        return voucherService.getAll();
//    }
//    @GetMapping("{id}")
//    public Voucher getOne(@PathVariable("id") Integer id) {
//        return voucherService.findByID(id);
//    }
//    @PostMapping
//    public Voucher post(@RequestBody Voucher voucher) {
//        voucherService.create(voucher);
//        return voucher;
//    }
//    @PutMapping("{id}")
//    public Voucher put(@PathVariable("id") Integer id, @RequestBody Voucher voucher) {
//        return voucherService.update(voucher);
//    }
//    @DeleteMapping("{id}")
//    public void delete(@PathVariable("id") Integer id) {
//        voucherService.delete(id);
//    }
//
//}
