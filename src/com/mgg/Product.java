package com.mgg;

/**
 * @author Kotipalli, Vasavi
 * @author Maloney, Madison Date: 2/25/21
 * 
 *         A Product class that extends to the Item class and represents the
 *         Products
 */
public class Product extends Item {

	private double basePrice;
	private double quantity;

	public Product(String code, String type, String name, double basePrice) {
		super(code, type, name);
		this.basePrice = basePrice;

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

	/**
	 * This method returns base price of a product depending on product type.
	 */
	public double getPrice() {
		if (getType().equals("PU")) {
			return Math.round(100 * 0.8 * getQuantity() * basePrice) / 100.0;
		} else if (getType().equals("PN")) {
			return getQuantity() * basePrice;
		} else if (getType().equals("PG")) {
			return getQuantity();
		}
		return 0.0;
	}

	/**
	 * This method returns the tax value given a product item base price.
	 */
	public double getTax() {
		return getPrice() * 0.0725;
	}

	public String toString() {
		if (getType().equals("PU")) {
			return getName() + "\n\t" + "(Used Item #" + getCode() + " @$" + basePrice + "/ea)\t\t\t\t$" + getPrice()
					+ "\n";
		} else if (getType().equals("PN")) {
			return getName() + "\n\t" + "(New Item #" + getCode() + " @$" + basePrice + "/ea)\t\t\t\t$" + getPrice()
					+ "\n";
		} else {
			return getName() + "\n\t" + "(Gift Card #" + getCode() + " ) \t\t\t\t\t@$" + getPrice() + "\n";
		}
	}

}