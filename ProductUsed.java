package com.mgg;

public class ProductUsed extends Product{

	public ProductUsed(String code, String type, String name, double basePrice) {
		super(code, type, name, basePrice);
	}
	
	public ProductUsed(String code, String type, String name, double basePrice, double quantity) {
		super(code, type, name, basePrice, quantity);
	}

	public double getPrice() {
		return Math.round(100 * 0.8 * getQuantity() * getBasePrice()) / 100.0;
	}

	@Override
	public String toString() {
		return getName() + "\n\t" + "(Used Item #" + getCode() + " @$" + getBasePrice() + "/ea)\t\t\t\t$" + getPrice()
		+ "\n";
	}
}
