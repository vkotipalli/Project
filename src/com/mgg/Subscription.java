package com.mgg;

public class Subscription {
	
	private String code;
	private String subscription;
	private String name;
	private double annualFee;
	
	public Subscription(String code, String subscription, String name, double annualFee) {
		super();
		this.code = code;
		this.subscription = subscription;
		this.name = name;
		this.annualFee = annualFee;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSubscription() {
		return subscription;
	}

	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAnnualFee() {
		return annualFee;
	}

	public void setAnnualFee(double annualFee) {
		this.annualFee = annualFee;
	}

	public String toString() {
		return code + "," + subscription + "," + name + "," + annualFee ;
	}

}
