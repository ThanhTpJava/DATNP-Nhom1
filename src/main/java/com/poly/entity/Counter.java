package com.poly.entity;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;

public class Counter {
	private int count;
	private LocalDate date;
	
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public Counter() {
		this.count = 0;
		this.date = LocalDate.now();
	}
	
	public int getCount() {
		return count;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void increment() {
		count++;
	}
	
	
	@Scheduled(fixedRate = 1000)
    	public void reportCurrentDate() {
        	System.out.println("Ngày hiện tại là: " + date);
        	String sql = "INSERT INTO DATE (CURRENT_DATE) VALUES (?)";
        	jdbcTemplate.update(sql, date);
    }

}
