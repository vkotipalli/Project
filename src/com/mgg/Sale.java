package com.mgg;

/**
 * @author Kotipalli, Vasavi
 * @author Maloney, Madison Date: 3/03/21
 * 
 *         Purpose of Program: A Sale class that represents the given attributes of the Sale object.
 */

import java.util.List;

public class Sale {
	private String code;
	private Person customer;
	private Store store;
	private Person salesperson;
	private List<Item> item;

	public Sale(String code, Person customer, Person salesperson, Store store, List<Item> item) {
		this.code = code;
		this.customer = customer;
		this.salesperson = salesperson;
		this.store = store;
		this.item = item;
	}

	public String getcode() {
		return code;
	}

	public Person getCustomer() {
		return customer;
	}

	public void setCustomer(Person customer) {
		this.customer = customer;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	public Person getSalesperson() {
		return salesperson;
	}

	public void setSalesperson(Person salesperson) {
		this.salesperson = salesperson;
	}

	public String toString() {
		return "Sales: #" + code + "\n" + "Store: #" + store.getStoreCode() + "\n" + "Customer:\n\t"
				+ customer.getLastName() + ", " + customer.getFirstName() + " (" + customer.getEmail() + ")" + "\n\t"
				+ customer.getCurrentAddress() + "\n" + "\nSales Person:\n\t" + salesperson.getLastName() + ", "
				+ salesperson.getFirstName() + " (" + salesperson.getEmail() + ")" + "\n\t"
				+ salesperson.getCurrentAddress() + "\n" + "\nItem\t\t\t\t\t\t\t\tTotal\n"
				+ "=-=-=-=-=-=-=-=-=-=-=-=-=-=\t\t\t\t\t=-=-=-=\n" + item.toString() + "\n"
				+ "\t\t\t\t\t\t\t\t--------";
	}

	/**
	 * This method calculates the subtotal of a specific sale that was made. A
	 * subtotal is all base prices taken into account before taxes and fees are
	 * applied.
	 * 
	 * @return subtotal
	 */
	public double getSubTotal() {
		double subtotal = 0.0;
		for (int i = 0; i < item.size(); i++) {
			subtotal += item.get(i).getPrice();
		}
		return Math.round(subtotal*100.0)/100.0;
	}

	/**
	 * This method calculates the total tax of a given sale. This includes the taxes
	 * for all items no matter product, service, or subscription.
	 * 
	 * @return tax
	 */
	public double getTotalTax() {
		double tax = 0.0;
		for (int i = 0; i < item.size(); i++) {
			tax += item.get(i).getTax();
		}
		return Math.round(tax*100.0)/100.0;
	}

	/**
	 * This method calculates the discount price depending on person type. This will
	 * be subtracted off the amount.
	 * 
	 * @return discountPrice
	 */
	public double getDiscountPrice() {
		double discountPrice = 0.0;
		discountPrice = (getSubTotal() + getTotalTax()) * customer.getDiscount();
		return Math.round(discountPrice*100.0)/100.0;
	}
	
	public double getTotalAmount() {
		double totalPrice = 0.0;
		totalPrice = getSubTotal() + getTotalTax() - getDiscountPrice();
		return Math.round(totalPrice*100.0)/100.0;
	}
	
}