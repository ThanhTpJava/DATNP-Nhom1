package com.poly.controller.adminControllers;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.poly.entity.Counter;
import com.poly.service.CounterService;

@Controller
public class CounterController {
	@Autowired
    private CounterService counterService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @GetMapping("/dates")
//    public String getAllDates(Model model) {
//
//        String sql = "SELECT * FROM DATE";
//        List<LocalDate> dates = jdbcTemplate.query(sql, new RowMapper<LocalDate>() {
//            @Override
//            public LocalDate mapRow(ResultSet rs, int rowNum) throws SQLException {
//
//                return rs.getDate("CURRENT_DATE").toLocalDate();
//            }
//        });
//
//        model.addAttribute("dates", dates);
//        // Trả về tên của view là dateForm.html
//        return "dateForm";
//    }
}
