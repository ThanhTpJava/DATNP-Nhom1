package com.poly.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.CounterRepository;

@Service
public class CounterService {
	@Autowired
    private CounterRepository counterRepository;

    // Phương thức để tăng số lượt truy cập lên 1 và cập nhật vào database theo ngày hiện tại
    public void incrementCounter() {
        LocalDate today = LocalDate.now();
        int count = counterRepository.getCounterByDate(today);
        count++;
        counterRepository.updateCounterByDate(count, today);
    }

    // Phương thức để lấy số lượt truy cập hiện tại từ database theo ngày hiện tại
    public int getCounter() {
        LocalDate today = LocalDate.now();
        return counterRepository.getCounterByDate(today);
    }
}
