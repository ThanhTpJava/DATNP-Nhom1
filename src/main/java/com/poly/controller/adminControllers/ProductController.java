package com.poly.controller.adminControllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.service.BannerService;
import com.poly.service.CounterAdminService;
import com.poly.service.CounterService;
import com.poly.dao.CounterAdminRepository;
import com.poly.entity.Banner;
import com.poly.entity.Counter;

@Controller
public class ProductController {
	
	@Autowired
	BannerService bannerService;
	
	@Autowired
	CounterAdminService cad;
	
	@Autowired
    private CounterService counterService;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/admin/index")
    public String Dashboard(Model model) {
        return "admin/index";
    }

    @RequestMapping("/admin/products")

    public String Product(Model model) {
        return "admin/products";
    }

    @RequestMapping("/admin/accounts")

    public String Account(Model model) {

        return "admin/accounts";
//        return "admin/authorize/roles";
    }
    
    @RequestMapping("/admin/orders")

    public String Order(Model model) {
        return "admin/order";
    }
    
    @RequestMapping("/admin/banners")
    public String Banner(Model model) {
    	model.addAttribute("List", bannerService.findAll());
    	model.addAttribute("banners", new Banner());
        return "admin/banner";
    }
    
    @RequestMapping("/user/abouts")
    public String About(Model model) {
        return "user/about";
    }
    //statistical
    
    @RequestMapping("/admin/statisticals")
    public String Statistical(Model model) {
    	counterService.incrementCounter();
        model.addAttribute("count", counterService.getCounter());
        
        String sql = "SELECT counter, date FROM counter";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        model.addAttribute("rows", rows);
        return "admin/statistical";
    }

}
