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
		return personCode + "," + type + "," + lastName + "," + firstName + "," + currentAddress + "," + email;
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
	 * This method creates a list of customers given the information from person and
	 * sale.
	 * 
	 * @return
	 */

//	public static List<Person> getCustomer() {
//		List<Person> person = DataLoadingFile.loadPersonFile();
//		List<Sale> sale = DataLoadingFile.loadSaleFile();
//		List<Person> customer = new ArrayList<>();
//		String personcode = "";
//		String lastName = "";
//		String firstName = "";
//		Address address = new Address("", "", "", "", "");
//		List<String> email = new ArrayList<>();
//		for (int i = 0; i < sale.size(); i++) {
//			for (int j = 0; j < person.size(); j++) {
//				if (sale.get(i).getCustomer().getPersonCode().equals(person.get(j).getPersonCode())) {
//					personcode = person.get(j).getPersonCode();
//					lastName = person.get(j).getLastName();
//					firstName = person.get(j).getFirstName();
//					address = person.get(j).getCurrentAddress();
//					email = person.get(j).getEmail();
//
//					Person p = new Person(personcode, lastName, firstName, address, email);
//					customer.add(p);
//				}
//			}
//		}
//		return customer;
//	}
//
	/**
	 * This method returns a list of the sales persons and makes sure there are no
	 * duplicates.
	 * 
	 * @return
	 */

	public static List<Person> getSalesPerson() {
		List<Person> person = DataLoadingFile.loadPersonFile();
		List<Sale> sale = DataLoadingFile.loadSaleFile();
		List<Person> saleperson = new ArrayList<>();
		String personcode = "";
		String lastName = "";
		String firstName = "";
		Address address = new Address("", "", "", "", "");
		int saleCount = 1;
		List<String> email = new ArrayList<>();

		for (int i = 0; i < sale.size(); i++) {
			for (int j = 0; j < person.size(); j++) {
				if (sale.get(i).getSalesperson().getPersonCode().equals(person.get(j).getPersonCode())) {
					personcode = person.get(j).getPersonCode();
					lastName = person.get(j).getLastName();
					firstName = person.get(j).getFirstName();
					address = person.get(j).getCurrentAddress();
					email = person.get(j).getEmail();

					for (int k = 0; k < saleperson.size(); k++) {
						if (saleperson.get(k).getPersonCode().equals(person.get(j).getPersonCode())) {
							saleCount = saleperson.get(k).getSaleCount() + 1;
							saleperson.remove(k);
						} else {
							saleCount = 1;
						}
					}

					Person p = new Person(personcode, lastName, firstName, address, email, saleCount);
					saleperson.add(p);
				}
			}
		}
		return saleperson;
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