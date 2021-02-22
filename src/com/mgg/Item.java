package com.mgg;

public class Item {
	private Product product;
	private Service service;
	private Subscription subscription;
	
	public Item(Product product, Service service, Subscription subscription) {
		super();
		this.product = product;
		this.service = service;
		this.subscription = subscription;
	}
	
	public Product getProduct() {
		return this.product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Service getService() {
		return this.service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public Subscription getSubscription() {
		return this.subscription;
	}
	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}
	
	
}
