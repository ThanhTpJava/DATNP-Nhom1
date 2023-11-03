package com.poly.controller.adminControllers;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

//    @GetMapping("/chart")
//    public String getChart() {
//        String sql = "SELECT COUNT(*) AS counter, date FROM counter GROUP BY date";
//        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
//        String chartData = "[['Date', 'Number of Visitors']";
//        for (Map<String, Object> row : rows) {
//            chartData += ",['" + row.get("date") + "', " + row.get("count") + "]";
//        }
//        chartData += "]";
//        return "<html><head><script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script><script type=\"text/javascript\">google.charts.load('current', {'packages':['corechart']});google.charts.setOnLoadCallback(drawChart);function drawChart() {var data = google.visualization.arrayToDataTable(" + chartData + ");var options = {title: 'Number of Visitors by Date',hAxis: {title: 'Date',  titleTextStyle: {color: '#333'}},vAxis: {minValue: 0}};var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));chart.draw(data, options);}</script></head><body><div id=\"chart_div\" style=\"width: 900px; height: 500px;\"></div></body></html>";
//    }
}
