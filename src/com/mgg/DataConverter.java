package com.mgg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class DataConverter {

	public static void main(String[] args) {
//		String id = Item.product.getId();
		//List to add instances of specific persons
		List<Person> personsList = new ArrayList<>();
		//List to add multiple optional email's
		List<String> emailList = new ArrayList<>();
		File f = new File("data/Persons.csv");
		Scanner s = null;
		
		try {
			s = new Scanner(f);
			int numOfPersons = Integer.parseInt(s.nextLine());
			System.out.println(numOfPersons);
			while (s.hasNextLine()) {
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
				while(i < token.length) {
					emailList.add(token[i]);
					i++;
					
				}
				
				
				Address address = new Address(street,city,state,zip,country);
				Person p = new Person(personCode,type,lastName,firstName,address,emailList);
				p.setPersonCode(personCode);
				p.setType(type);
				p.setLastName(lastName);
				p.setFirstName(firstName);
				p.setCurrentAddress(address);
				p.setEmail(emailList);
				personsList.add(p);
				address.toString();
				System.out.println(p);
				emailList.clear();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
		s.close();
	}
	

}