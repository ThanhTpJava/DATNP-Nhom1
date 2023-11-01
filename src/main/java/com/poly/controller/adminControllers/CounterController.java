package com.poly.controller.adminControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.poly.entity.Counter;
import com.poly.service.CounterService;

@Controller
public class CounterController {
	@Autowired
    private CounterService counterService;

    // Xử lý request GET tới đường dẫn "/"
//    @GetMapping("/")
//    public String showHomePage(Model model) {
//        // Tăng số lượt truy cập lên 1 và cập nhật vào database
//        counterService.incrementCounter();
//        // Lấy số lượt truy cập hiện tại từ database và thêm vào model để hiển thị trên view
//        model.addAttribute("count", counterService.getCounter());
//        // Trả về tên view là "index"
//        return "index";
//    }
}
