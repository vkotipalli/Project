package com.mgg;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author Kotipalli, Vasavi
 * @author Maloney, Madison Date: 2/25/21
 * 
 *         A Subscription class that extends to the Item class and represents
 *         the Subscriptions
 */
public class Subscription extends Item {

	private double annualFee;
	private LocalDate beginDate;
	private LocalDate endDate;

	public Subscription(String code, String type, String name, double annualFee) {
		super(code, name, type);
		this.annualFee = annualFee;
	}
	
	public Subscription(String code, String name, String type, double annualFee, LocalDate beginDate, LocalDate endDate) {
		super(code, name, type);
		this.annualFee = annualFee;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	public double getAnnualFee() {
		return annualFee;
	}

	public void setAnnualFee(double annualFee) {
		this.annualFee = annualFee;
	}

	public LocalDate getBeginDate() {
		return beginDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void addBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

	public void addEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * This method returns the duration between the begin and end of the
	 * subscription.
	 * 
	 * @return duration
	 */
	public long durationDays() {
		return (ChronoUnit.DAYS.between(beginDate, endDate) + 1);
	}

	/**
	 * This method returns base price of a subscription.
	 */
	public double getPrice() {
		return Math.round((durationDays() / 365.0) * annualFee * 100.0) / 100.0;
	}

	/**
	 * This method returns the tax on a subscription.
	 */
	public double getTax() {
		return 0.0;
	}

	public String toString() {
		return getName() + "\n\t" + "(Subscription #" + getCode() + " " + durationDays() + " days@" + annualFee
				+ "/year)\t\t$" + getPrice() + "\n";
	}

}