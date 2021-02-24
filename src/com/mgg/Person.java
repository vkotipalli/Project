package com.mgg;

//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;

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
	
//	public List<Person> loadPersonFile(){
//		File f = new File("data/Persons.csv");
//		Scanner s = null;
//		List<Person> personsList = new ArrayList<>();
//		List<String> emailList = new ArrayList<>();
//
//		try {
//			s = new Scanner(f);
//			int numOfPersons = Integer.parseInt(s.nextLine());
//			System.out.println(numOfPersons);
//			while (s.hasNextLine()) {
//				String line = s.nextLine();
//				String token[] = line.split(",");
//				String personCode = token[0];
//				String type = token[1];
//				String lastName = token[2];
//				String firstName = token[3];
//				String street = token[4];
//				String city = token[5];
//				String state = token[6];
//				String zip = token[7];
//				String country = token[8];
//
//				int i = 9;
//				while (i < token.length) {
//					emailList.add(token[i]);
//					i++;
//
//				}
//
//				Address address = new Address(street, city, state, zip, country);
//				Person p = new Person(personCode, type, lastName, firstName, address, emailList);
//				p.setPersonCode(personCode);
//				p.setType(type);
//				p.setLastName(lastName);
//				p.setFirstName(firstName);
//				p.setCurrentAddress(address);
//				p.setEmail(emailList);
//				personsList.add(p);
//				address.toString();
//				System.out.println(p);
//				emailList.clear();
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		s.close();
//		
//		return personsList;
//	}
	
	
	
}
