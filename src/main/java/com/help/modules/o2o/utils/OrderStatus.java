package com.help.modules.o2o.utils;

public enum OrderStatus {
	CREATE(1, "代付款");
	
	private Integer id;
	private String name;
	
	
	OrderStatus(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
}
