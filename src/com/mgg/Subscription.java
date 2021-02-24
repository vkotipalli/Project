package com.mgg;

public class Subscription extends Item {

	private double annualFee;

	public Subscription(String code, String type, String name, double annualFee) {
		super(code, name, type);
		this.annualFee = annualFee;
	}

	public double getAnnualFee() {
		return annualFee;
	}

	public void setAnnualFee(double annualFee) {
		this.annualFee = annualFee;
	}

	@Override
	public String toString() {
		return getCode() + "," + getType() + "," + getName() + "," + annualFee;
	}

}