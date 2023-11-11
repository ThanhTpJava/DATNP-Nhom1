package com.poly.controller.adminControllers;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.poly.dao.DataDAO;
import com.poly.entity.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dto.AccountDTO;
import com.poly.entity.Account;
import com.poly.service.AccountService;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductAdminController {

	@Autowired
	AccountService accService;

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

//    @RequestMapping("/admin/accounts")
//
//    public String Account(Model model,
//    		@RequestParam(name = "role", required = false, defaultValue = "All") String role) {
//
//    	List<AccountDTO> lisAccountDTOs;
//
//    	if("All".equalsIgnoreCase(role)) {
//    		lisAccountDTOs = accService.findAllAccountDTO();
//    	}else {
//    		lisAccountDTOs = accService.findAccountDTOByRole(role);
//    		if (lisAccountDTOs.isEmpty()) {
//				System.out.println("Rỗng");
//			}
//		}
//
//    	model.addAttribute("listAccounts", lisAccountDTOs);
//
//        return "admin/accounts";
//
//    }

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
