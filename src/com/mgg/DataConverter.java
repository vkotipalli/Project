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
//		List<Person> personsList = new ArrayList<>();
//		//List to add multiple optional email's
//		List<String> emailList = new ArrayList<>();
//		File f = new File("data/Persons.csv");
//		Scanner s = null;
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
//				while(i < token.length) {
//					emailList.add(token[i]);
//					i++;
//					
//				}
//				
//				
//				Address address = new Address(street,city,state,zip,country);
//				Person p = new Person(personCode,type,lastName,firstName,address,emailList);
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
//					itemsList.add(pr);
				}else if(type.equals("SV")) {
					double hourlyRate = Double.parseDouble(token[3]);
					Service sv = new Service(code,type,name,hourlyRate);
					sv.setCode(code);
					sv.setService(type);
					sv.setName(name);
					sv.setHourlyRate(hourlyRate);
					System.out.println(sv);
//					itemsList.add(sv);
				}else if(type.equals("SB")) {
					double annualFee = Double.parseDouble(token[3]);
					Subscription sb = new Subscription(code,type,name,annualFee);
//					itemsList.add(sb);
					sb.setCode(code);
					sb.setSubscription(type);
					sb.setName(name);
					sb.setAnnualFee(annualFee);
					System.out.println(sb);	
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
		scan.close();
		
	}
	

}
