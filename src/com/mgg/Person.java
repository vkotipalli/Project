package com.mgg;

import java.util.List;

public class Person {
	private String personCode;
	private String type;
	private String lastName;
	private String firstName;
	private Address currentAddress;
	private List<String> email;
	
	public Person(String personCode, String type, String lastName, String firstName, Address currentAddress, List<String> email) {
//		super();
		this.personCode = personCode;
		this.type = type;
		this.lastName = lastName;
		this.firstName = firstName;
		this.currentAddress = currentAddress;
		this.email = email;
	}
	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Address getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(Address currentAddress) {
		this.currentAddress = currentAddress;
	}

	public List<String> getEmail() {
		return email;
	}

	public void setEmail(List<String> email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return personCode+ "," + type + "," + lastName + "," + firstName + ","
				+ currentAddress + "," + email;
	}
	
	
	
}
