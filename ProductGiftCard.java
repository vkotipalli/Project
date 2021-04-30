package com.mgg;

public class ProductGiftCard extends Product {

	public ProductGiftCard(String code, String type, String name, double basePrice) {
		super(code, type, name, basePrice);
	}

	@Override
	public double getPrice() {
		return Math.round(getBasePrice() * 100.0) / 100.0;
	}

	@Override
	public String toString() {
		return getName() + "\n\t" + "(Gift Card #" + getCode() + " ) \t\t\t\t\t$" + getPrice() + "\n";
	}
}
