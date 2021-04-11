package com.mgg;

/**
 * @author Kotipalli, Vasavi
 * @author Maloney, Madison Date: 2/25/21
 * 
 *         An abstract class that represents the Item object that's specified
 *         for the project
 */
public class ProductNew extends Product {

	public ProductNew(String code, String type, String name, double basePrice) {
		super(code, type, name, basePrice);
	}

	public ProductNew(String code, String type, String name, double basePrice, double quantity) {
		super(code, type, name, basePrice, quantity);
	}

	public double getPrice() {
		return getQuantity() * getBasePrice();
	}

	@Override
	public String toString() {
		return getName() + "\n\t" + "(New Item #" + getCode() + " @$" + getBasePrice() + "/ea)\t\t\t\t$" + getPrice()
				+ "\n";
	}
}
