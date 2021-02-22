package com.mgg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class DataConverter {

	public static void main(String[] args) {
//		String id = Item.product.getId();
		List<Person> personsList = new ArrayList<>();
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
				
				String email = null;
//				int i = 8;
//				while(!token[i].isEmpty()) {
//					token[i]= email;
//					i++;
//				}
//				System.out.println(line);
//				int i = 8;
//				if(token.length >= 8) {
//					String email = null;
//					while(i<=token.length) {
//						email = token[i];
//						i++;
//					}
//					
//				}
//				
				Address address = new Address(street,city,state,zip,country);
				Person p = new Person(personCode,type,lastName,firstName,address,email);
				p.setPersonCode(personCode);
				p.setType(type);
				p.setLastName(lastName);
				p.setFirstName(firstName);
				p.setCurrentAddress(address);
//				p.setEmail(email);
				personsList.add(p);
				System.out.println(p);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
		s.close();
	}
	

}
