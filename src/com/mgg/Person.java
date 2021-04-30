package com.mgg;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kotipalli, Vasavi
 * @author Maloney, Madison Date: 2/25/21
 * 
 *         A class that represents the Person object that's specified for the
 *         project
 */

public class Person {
	private String personCode;
	private String type;
	private String lastName;
	private String firstName;
	private Address currentAddress;
	private List<String> email;
	private int saleCount;

	public Person(String personCode, String type, String lastName, String firstName, Address currentAddress,
			List<String> email) {
		this.personCode = personCode;
		this.type = type;
		this.lastName = lastName;
		this.firstName = firstName;
		this.currentAddress = currentAddress;
		this.email = email;
	}

	public Person(String personCode, String lastName, String firstName, Address currentAddress, List<String> email) {
		this.personCode = personCode;
		this.lastName = lastName;
		this.firstName = firstName;
		this.currentAddress = currentAddress;
		this.email = email;
	}

	public Person(String personCode, String lastName, String firstName, Address currentAddress, List<String> email,
			int saleCount) {
		this.personCode = personCode;
		this.lastName = lastName;
		this.firstName = firstName;
		this.currentAddress = currentAddress;
		this.email = email;
		this.saleCount = saleCount;
	}
//	public Person(String personCode, String type, String lastName, String firstName, Address currentAddress) {
//		this.personCode = personCode;
//		this.type = type;
//		this.lastName = lastName;
//		this.firstName = firstName;
//		this.currentAddress = currentAddress;
//	}

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

	public int getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}
	
	

	public String toString() {
		return personCode + "," + type + "," + lastName + "," + firstName + "," + currentAddress + "," + email+"\n";
	}
	
	public void addEmail(String emails) {
		if ("".equals(emails)) {
			System.out.println("jifw");
		} else {
			this.email.add(emails);
		}
	}

	/**
	 * This method gets a specific instance on a Person (and its attributes) based
	 * on a given person code. It returns null if the person code given is not found
	 * within the person list.
	 * 
	 * @param personCode
	 * @param personList
	 * @return specific person instance or null
	 */
	public static Person getPerson(String personCode, List<Person> personList) {

		for (int i = 0; i < personList.size(); i++) {
			if (personList.get(i).getPersonCode().equals(personCode)) {
				return personList.get(i);
			}
		}
		return null;
	}

	/**
	 * This method gets the discount percentage based on a specific person type. It
	 * only returns the pure percentage, no calculations are made.
	 * 
	 * @return discount percentage
	 */
	public double getDiscount() {
		if (getType().equals("G")) {
			return 0.05;
		} else if (getType().equals("P")) {
			return 0.10;
		} else if (getType().equals("E")) {
			return 0.15;
		} else {
			return 0.0;
		}
	}

	
}