package com.mgg;

/**
 * @author Kotipalli, Vasavi
 * @author Maloney, Madison 
 * Date: 2/25/21
 * 
 * A Service class that extends to the Item class and represents the Services
 */
public class Service extends Item {
	private double hourlyRate;
	private String employeeCode;
	private int numberOfHours;

	public Service(String code, String name, String type, double hourlyRate) {
		super(code, name, type);
		this.hourlyRate = hourlyRate;
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	
	public String getEmployeeCode() {
		return employeeCode;
	}
	public int getNumberOfHours() {
		return numberOfHours;
	}

	public String toString() {
		return getCode() + "," + getType() + "," + getName() + "," + hourlyRate;
	}

}