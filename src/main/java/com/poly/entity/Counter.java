package com.poly.entity;

import java.time.LocalDate;

public class Counter {
	private int count;
	private LocalDate date;
	
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
}
