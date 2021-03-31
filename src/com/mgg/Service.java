package com.mgg;

/**
 * @author Kotipalli, Vasavi
 * @author Maloney, Madison Date: 2/25/21
 * 
 *         A Service class that extends to the Item class and represents the
 *         Services
 */
public class Service extends Item {
	private double hourlyRate;
	private Person employeeCode;
	private double numberOfHours;

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

	public Person getEmployeeCode() {
		return employeeCode;
	}

	public double getNumberOfHours() {
		return numberOfHours;
	}

	public void addEmployeeCode(Person employeeCode) {
		this.employeeCode = employeeCode;
	}

	public void addNumberOfHours(double numberOfHours) {
		this.numberOfHours = numberOfHours;
	}

	/**
	 * This method computes the base price of a service.
	 */
	public double getPrice() {
		return hourlyRate * getNumberOfHours();
	}

	/**
	 * This method computes the tax of a service given the base price.
	 */
	public double getTax() {
		return getPrice() * 0.0285;
	}

	public String toString() {
		return getName() + "\n\t" + "(Service #" + getCode() + " by " + getEmployeeCode().getLastName() + ", "
				+ getEmployeeCode().getFirstName() + " " + getNumberOfHours() + "hrs@$" + hourlyRate + "/hr)\t$"
				+ getPrice() + "\n";
	}

}