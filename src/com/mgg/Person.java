package com.mgg;

import unl.cse.Address;

public class Person {
	private Character type;
	private String lastName;
	private String firstName;
	private Address currentAddress;
	private String email;
	
	public Person(Character type, String lastName, String firstName, Address currentAddress, String email) {
//		super();
		this.type = type;
		this.lastName = lastName;
		this.firstName = firstName;
		this.currentAddress = currentAddress;
		this.email = email;
	}

	public Character getType() {
		return type;
	}

	public void setType(Character type) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
