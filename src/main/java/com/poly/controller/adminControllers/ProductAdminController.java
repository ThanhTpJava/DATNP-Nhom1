package com.poly.controller.adminControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.text.SimpleDateFormat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.poly.dao.DataDAO;
import com.poly.entity.Data;

@Controller
public class ProductAdminController {
	@Autowired
	DataDAO dataDAO;


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
    
    @RequestMapping("/statistical")
    public String Statis(Model model) {
        return "admin/statistical";
    }
    
//    @RequestMapping("/ac")
//    public String ac(Model model) {
//        return "admin/ac";
//    }

    @RequestMapping("/thongke")
	@ResponseBody
	public String getDataFromDB() {
		List<Data> dataList = dataDAO.findAll();
		
		
		JsonArray jsonDate = new JsonArray();
		JsonArray jsonCount = new JsonArray();
		JsonObject json = new JsonObject();
		dataList.forEach(data->{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	        String strDate = formatter.format(data.getDate());
			jsonDate.add(strDate);
			jsonCount.add(data.getCounter());
			System.out.println(strDate);
		});
		json.add("date", jsonDate);
		json.add("counter", jsonCount);
		return json.toString();
	}

}
