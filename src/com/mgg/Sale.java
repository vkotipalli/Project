package com.mgg;

public class Sale {
	private String code;
	private Person customer;
	private Store store;
	private Item item;
		
	public Sale(Person customer, Store store, Item item) {
		super();
		this.customer = customer;
		this.store = store;
		this.item = item;
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
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
}
