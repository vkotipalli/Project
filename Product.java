package com.mgg;

/**
 * @author Kotipalli, Vasavi
 * @author Maloney, Madison Date: 2/25/21
 * 
 *         A Product class that extends to the Item class and represents the
 *         Products
 */
public abstract class Product extends Item {

	private double basePrice;
	private double quantity;

	public Product(String code, String type, String name, double basePrice) {
		super(code, type, name);
		this.basePrice = basePrice;

	}

	public Product(String code, String type, String name, double basePrice, double quantity) {
		super(code, type, name);
		this.basePrice = basePrice;
		this.quantity = quantity;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public double getQuantity() {
		return quantity;
	}

	public void addQuantity(double quantity) {
		this.quantity = quantity;
	}

	public abstract double getPrice();

	public double getTax() {
		return getPrice() * 0.0725;
	}

}