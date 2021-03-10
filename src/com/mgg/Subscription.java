package com.mgg;

import java.time.LocalDate;

/**
 * @author Kotipalli, Vasavi
 * @author Maloney, Madison 
 * Date: 2/25/21
 * 
 * A Subscription class that extends to the Item class and represents the Subscriptions
 */
public class Subscription extends Item {

	private double annualFee;
	private LocalDate beginDate; 
	private LocalDate endDate; 

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
	public LocalDate getbeginDate() {
		return beginDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}

	public String toString() {
		return getCode() + "," + getType() + "," + getName() + "," + annualFee;
	}
	
	public void addBeingDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}
	
	public void addEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
