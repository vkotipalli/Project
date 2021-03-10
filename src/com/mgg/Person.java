package com.mgg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Kotipalli, Vasavi
 * @author Maloney, Madison 
 * Date: 2/25/21
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

	public Person(String personCode, String type, String lastName, String firstName, Address currentAddress,
			List<String> email) {
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

	public String toString() {
		return personCode + "," + type + "," + lastName + "," + firstName + "," + currentAddress + "," + email;
	}

	/**
	 * Takes in the information from the "Persons.csv" file, reads the file line by
	 * line, tokenizes it, ands it to a list.
	 * 
	 * @return
	 */
	public static List<Person> loadPersonFile() {
		File f = new File("data/Persons.csv");
		Scanner s = null;
		List<Person> personsList = new ArrayList<>();

		try {
			s = new Scanner(f);
			// variable stores number of items , could use for future assignments
			int numOfPersons = Integer.parseInt(s.nextLine());
			while (s.hasNextLine()) {
				List<String> emailList = new ArrayList<>();
				String line = s.nextLine();
				String token[] = line.split(",");
				String personCode = token[0];
				String type = token[1];
				String lastName = token[2];
				String firstName = token[3];
				String street = token[4];
				String city = token[5];
				String state = token[6];
				String zip = token[7];
				String country = token[8];

				int i = 9;
				while (i < token.length) {
					emailList.add(token[i]);
					i++;
				}

				Address address = new Address(street, city, state, zip, country);
				Person p = new Person(personCode, type, lastName, firstName, address, emailList);
//				System.out.println(p);
//				p.setPersonCode(personCode);
//				p.setType(type);
//				p.setLastName(lastName);
//				p.setFirstName(firstName);
//				p.setCurrentAddress(address);
//				p.setEmail(emailList);
				personsList.add(p);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		s.close();
		return personsList;
	}

	/**
	 * Takes in the list from loadPersonFile() and the file path to where the json
	 * is going to output to. After the conversion is done, the information will
	 * printed on to the Person.json file.
	 * 
	 * @param itemsList
	 * @param filePath
	 */
	public static void personFileToJson(List<Person> personsList, String filePath) {
		File outputFile = new File(filePath);
		PrintWriter personOutput = null;
		try {
			personOutput = new PrintWriter(outputFile);
			GsonBuilder builder = new GsonBuilder();
			builder.setPrettyPrinting();
			Gson gson = builder.create();
			personOutput.print(gson.toJson(personsList));
			personOutput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}