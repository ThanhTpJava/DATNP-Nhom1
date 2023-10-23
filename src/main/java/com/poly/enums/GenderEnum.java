package com.poly.enums;

public enum GenderEnum {
	FEMALE(0),
	MALE(1),
	UNKNOW(2);
	
	private final int value;
	
	private GenderEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
