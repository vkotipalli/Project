package com.mgg;

/**
 * @author Kotipalli, Vasavi
 * @author Maloney, Madison 
 * Date: 2/25/21
 * 
 * A Product class that extends to the Item class and represents the Products
 */
public class Product extends Item {

	private double basePrice;

	public Product(String code, String type, String name, double basePrice) {
		super(code, type, name);
		this.basePrice = basePrice;
	}

	public double getBasePrice() {
		return this.basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public String toString() {
		return getCode() + "," + getType() + "," + getName() + "," + basePrice;
	}

}