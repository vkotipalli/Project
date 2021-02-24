package com.mgg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;






//For XML use a printwriter to write the xml list to the output file.

public class DataConverter {
	public static List<Item> loadItemFile() {
		List<Item> itemsList =new ArrayList<>();
		File file = new File("data/Items.csv");
		Scanner scan = null;
		try {
			scan = new Scanner(file);
			int numOfItems = Integer.parseInt(scan.nextLine());
			System.out.println(numOfItems);
			
			while (scan.hasNextLine()) {
				Item i = null;
				String line = scan.nextLine();
				String token[] = line.split(",");
				String code = token[0];
				String type = token[1];
				String name = token[2];
				if(type.equals("PN")||type.equals("PU")||type.equals("PG")) {
					double basePrice;
					if(token.length == 3) {
						basePrice = 0.00;
					}else {
						basePrice = Double.parseDouble(token[3]);
					}
					Product pr = new Product(code,type,name,basePrice);
					pr.setCode(code);
					pr.setType(type);
					pr.setName(name);
					pr.setBasePrice(basePrice);
					System.out.println(pr);
					itemsList.add(pr);
				}else if(type.equals("SV")) {
					double hourlyRate = Double.parseDouble(token[3]);
					Service sv = new Service(code,type,name,hourlyRate);
					sv.setCode(code);
					sv.setType(type);
					sv.setName(name);
					sv.setHourlyRate(hourlyRate);
					System.out.println(sv);
					itemsList.add(sv);
				}else if(type.equals("SB")) {
					double annualFee = Double.parseDouble(token[3]);
					Subscription sb = new Subscription(code,type,name,annualFee);
					itemsList.add(sb);
					sb.setCode(code);
					sb.setType(type);
					sb.setName(name);
					sb.setAnnualFee(annualFee);
					System.out.println(sb);	
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
		scan.close();
		return itemsList;
	}
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
		File outputPersonFile = new File("data/personsTestCase1.json");
		Scanner s = null;
		PrintWriter personOutput = null;
		List<Person> personsList = new ArrayList<>();
		List<String> emailList = new ArrayList<>();
		List<String>xmlList = new ArrayList<>();

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
		
//		try {
//		personOutput = new PrintWriter(outputPersonFile);
//		GsonBuilder builder = new GsonBuilder();
//		builder.setPrettyPrinting();
//		Gson gson = builder.create();
//		System.out.println(gson.toJson(personsList));
//		personOutput.print("Hello");
//		personOutput.print(gson.toJson(personsList));
//		
////		xmlList.add(xml);
////		personOutput.print(xml);
////		System.out.println(xml);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//		//System.out.println(xml);
//		
		return personsList;
	}

	public static void main(String[] args) {
		
		List<Person> personsList = loadPersonFile();

		System.out.println("");

		List<Store> storesList = loadStoreFile();
		
		System.out.println("");
		
		List<Item> itemsList = loadItemFile();
		
		File outputFile = new File("data/Persons.json");
		PrintWriter personOutput = null;
		try { 
		personOutput = new PrintWriter(outputFile);
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		System.out.println(gson.toJson(personsList));
		personOutput.print(gson.toJson(personsList));
		personOutput.close();
		} catch (FileNotFoundException e ) {
			e.printStackTrace();
		}
		
//		XStream xstream = new XStream();
////		xstream.alias("person", Person.class);
////		xstream.alias("address", Address.class);
////		String xml = xstream.toXML(personsList);
//		
		//System.out.println(xml);
		
	}
}