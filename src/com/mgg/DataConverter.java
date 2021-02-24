package com.mgg;

import java.io.File;
import java.io.FileNotFoundException;
//import java.io.PrintWriter;
import java.util.Scanner;

//import com.thoughtworks.xstream.XStream;

import java.util.List;
import java.util.ArrayList;

public class DataConverter {
	
	public static List<Store> loadStoreFile() {
		File storesInput = new File("data/Stores.csv");
		List<Store> storesList = new ArrayList<>();
		Scanner s = null;
		try {
			s = new Scanner(storesInput);
			int numOfStores = Integer.parseInt(s.nextLine());
			System.out.println(numOfStores);
			while(s.hasNextLine()) {
				String line = s.nextLine();
				String token[] = line.split(",");
				String storeCode = token[0];
				String managerCode = token[1];
				String street = token[2];
				String city = token[3];
				String state = token[4];
				String zip = token[5];
				String country = token[6];
				
				Address storeAddress = new Address(street,city,state,zip,country);
				Store gameStore = new Store(storeCode,managerCode,storeAddress);
				
				gameStore.setStoreCode(storeCode);
				gameStore.setManagerCode(managerCode);
				gameStore.setStoreAddress(storeAddress);
				storesList.add(gameStore);
				gameStore.toString();
				System.out.println(gameStore);
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		
		return storesList;
	}
	
	public static List<Person> loadPersonFile(){
		File f = new File("data/Persons.csv");
		Scanner s = null;
		List<Person> personsList = new ArrayList<>();
		List<String> emailList = new ArrayList<>();

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
				while (i < token.length) {
					emailList.add(token[i]);
					i++;

				}

				Address address = new Address(street, city, state, zip, country);
				Person p = new Person(personCode, type, lastName, firstName, address, emailList);
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
		
		return personsList;
	}

	public static void main(String[] args) {
		
		List<Person> personsList = loadPersonFile();

		System.out.println("");
		

		List<Store> storesList = loadStoreFile();
		

	}
}