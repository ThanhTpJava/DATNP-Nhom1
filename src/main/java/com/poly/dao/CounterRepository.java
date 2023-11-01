package com.poly.dao;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CounterRepository {
	@Autowired
    private JdbcTemplate jdbcTemplate;

    // Phương thức để lấy số lượt truy cập từ database theo ngày
    public int getCounterByDate(LocalDate date) {
        String sql = "SELECT counter FROM counter WHERE date = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, date);
    }

    // Phương thức để cập nhật số lượt truy cập trong database theo ngày
    public void updateCounterByDate(int count, LocalDate date) {
        String sql = "UPDATE counter SET counter = ? WHERE date = ?";
        jdbcTemplate.update(sql, count, date);
    }
}
