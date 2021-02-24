package com.mgg;

public class Product {
	private String code;
	private String type;
	private String name;
	private double basePrice;
	
	public Product(String code, String type, String name, double basePrice) {
		super();
		this.code = code;
		this.type = type;
		this.name = name;
		this.basePrice = basePrice;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBasePrice() {
		return this.basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	@Override
	public String toString() {
		return code + "," + type + "" + name + "," + basePrice;
	}
	
	
}
