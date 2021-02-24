package com.mgg;

public class Service extends Item {
	private double hourlyRate;

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

	@Override
	public String toString() {
		return getCode() + "," + getType() + "," + getName() + "," + hourlyRate;
	}

}