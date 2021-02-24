package com.mgg;

public class Service {
	private String code;
	private String service;
	private String name;
	private Double hourlyRate;
	public Service(String code, String service, String name, Double hourlyRate) {
		super();
		this.code = code;
		this.service = service;
		this.name = name;
		this.hourlyRate = hourlyRate;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getHourlyRate() {
		return hourlyRate;
	}
	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	@Override
	public String toString() {
		return code + "," + service + "," + name + "," + hourlyRate;
	}
	
}
