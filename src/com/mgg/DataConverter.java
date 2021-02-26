package com.mgg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Kotipalli, Vasavi
 * @author Maloney, Madison 
 * Date: 2/25/21
 * 
 *         Purpose of Program: This a data conversion program that takes in a
 *         csv file for each respective class (Person, Store, Item). Then we
 *         used the loadFile methods to get the information by reading it and
 *         tokenizing it. We then used our FileToJson method to output the
 *         information of each respective class into a json file.
 */
public class DataConverter {

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
				p.setPersonCode(personCode);
				p.setType(type);
				p.setLastName(lastName);
				p.setFirstName(firstName);
				p.setCurrentAddress(address);
				p.setEmail(emailList);
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

	/**
	 * Takes in the information from the "Stores.csv" file, reads the file line by
	 * line, tokenizes it, ands it to a list.
	 * 
	 * @return
	 */
	public static List<Store> loadStoreFile() {
		File storesInput = new File("data/Stores.csv");
		List<Store> storesList = new ArrayList<>();
		Scanner s = null;
		try {
			s = new Scanner(storesInput);
			int numOfStores = Integer.parseInt(s.nextLine());
			while (s.hasNextLine()) {
				String line = s.nextLine();
				String token[] = line.split(",");
				String storeCode = token[0];
				String managerCode = token[1];
				String street = token[2];
				String city = token[3];
				String state = token[4];
				String zip = token[5];
				String country = token[6];

				Address storeAddress = new Address(street, city, state, zip, country);
				Store gameStore = new Store(storeCode, managerCode, storeAddress);

				gameStore.setStoreCode(storeCode);
				gameStore.setManagerCode(managerCode);
				gameStore.setStoreAddress(storeAddress);
				storesList.add(gameStore);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return storesList;
	}

	/**
	 * Takes in the list from storeFileToJson() and the file path to where the json
	 * is going to output to. After the conversion is done, the information will
	 * printed on to the Stores.json file.
	 * 
	 * @param itemsList
	 * @param filePath
	 */
	public static void storeFileToJson(List<Store> storesList, String filePath) {
		File outputFileStores = new File("data/Stores.json");
		PrintWriter storeOutput = null;
		try {
			storeOutput = new PrintWriter(outputFileStores);
			GsonBuilder builder = new GsonBuilder();
			builder.setPrettyPrinting();
			Gson gson = builder.create();
			storeOutput.print(gson.toJson(storesList));
			storeOutput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Takes in the information from the "Items.csv" file, reads the file line by
	 * line, tokenizes it, ands it to a list.
	 * 
	 * @return
	 */
	public static List<Item> loadItemFile() {
		List<Item> itemsList = new ArrayList<>();
		File file = new File("data/Items.csv");
		Scanner scan = null;
		try {
			scan = new Scanner(file);
			int numOfItems = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				String token[] = line.split(",");
				String code = token[0];
				String type = token[1];
				String name = token[2];
				if (type.equals("PN") || type.equals("PU") || type.equals("PG")) {
					double basePrice;
					if (token.length == 3) {
						basePrice = 0.00;
					} else {
						basePrice = Double.parseDouble(token[3]);
					}
					Product pr = new Product(code, type, name, basePrice);
					pr.setCode(code);
					pr.setType(type);
					pr.setName(name);
					pr.setBasePrice(basePrice);
					itemsList.add(pr);
				} else if (type.equals("SV")) {
					double hourlyRate = Double.parseDouble(token[3]);
					Service sv = new Service(code, type, name, hourlyRate);
					sv.setCode(code);
					sv.setType(type);
					sv.setName(name);
					sv.setHourlyRate(hourlyRate);
					itemsList.add(sv);
				} else if (type.equals("SB")) {
					double annualFee = Double.parseDouble(token[3]);
					Subscription sb = new Subscription(code, type, name, annualFee);
					sb.setCode(code);
					sb.setType(type);
					sb.setName(name);
					sb.setAnnualFee(annualFee);
					itemsList.add(sb);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		scan.close();
		return itemsList;
	}

	/**
	 * Takes in the list from loadItemFile() and the file path to where the json is
	 * going to output to. After the conversion is done, the information will
	 * printed on to the Items.json file.
	 * 
	 * @param itemsList
	 * @param filePath
	 */
	public static void itemsFileToJson(List<Item> itemsList, String filePath) {
		File outputFileItems = new File("data/Items.json");
		PrintWriter itemOutput = null;
		try {
			itemOutput = new PrintWriter(outputFileItems);
			GsonBuilder builder = new GsonBuilder();
			builder.setPrettyPrinting();
			Gson gson = builder.create();
			itemOutput.print(gson.toJson(itemsList));
			itemOutput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		List<Person> personsList = loadPersonFile();
		personFileToJson(personsList, "data/Persons.json");

		System.out.println("");

		List<Store> storesList = loadStoreFile();
		storeFileToJson(storesList, "data/Stores.json");

		System.out.println("");

		List<Item> itemsList = loadItemFile();
		itemsFileToJson(itemsList, "data/Items.json");
	}
}