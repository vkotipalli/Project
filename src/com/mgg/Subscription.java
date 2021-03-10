package com.mgg;

import java.util.Date;

/**
 * @author Kotipalli, Vasavi
 * @author Maloney, Madison 
 * Date: 2/25/21
 * 
 * A Subscription class that extends to the Item class and represents the Subscriptions
 */
public class Subscription extends Item {

	private double annualFee;
	private Date beginDate; 
	private Date endDate; 

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
	public Date getbeginDate() {
		return beginDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}

	public String toString() {
		return getCode() + "," + getType() + "," + getName() + "," + annualFee;
	}

}
