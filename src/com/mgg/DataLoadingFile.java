package com.mgg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Kotipalli, Vasavi
 * @author Maloney, Madison Date: 3/03/21
 * 
 *         Purpose of Program: This is a data loading file. For each person,
 *         store, item, and sale class when taking in their respective .csv file
 *         data there are methods for each class to read the file line by line,
 *         tokenize it, and add the data to a respective list. For the person,
 *         store, and item classes there are methods to convert the data to json
 *         file format given a specific output file.
 */

public class DataLoadingFile {

	/**
	 * Takes in the information from the "Persons.csv" file, reads the file line by
	 * line, tokenizes it, and adds it to a list.
	 * 
	 * @return List<Person> personList
	 */

	public static List<Person> loadPersonFile() {
		File f = new File("data/Persons.csv");
		Scanner s = null;
		List<Person> personsList = new ArrayList<>();

		try {
			s = new Scanner(f);
			s.nextLine();
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
	 * line, tokenizes it, and adds it to a list.
	 * 
	 * @return List<Store> storeList
	 */
	public static List<Store> loadStoreFile() {
		File storesInput = new File("data/Stores.csv");
		List<Store> storesList = new ArrayList<>();
		Scanner s = null;
		try {
			s = new Scanner(storesInput);
			s.nextLine();
			while (s.hasNextLine()) {
				String line = s.nextLine();
				String token[] = line.split(",");
				String storeCode = token[0];
				Person managerCode = Person.getPerson(token[1], loadPersonFile());
				String street = token[2];
				String city = token[3];
				String state = token[4];
				String zip = token[5];
				String country = token[6];

				Address storeAddress = new Address(street, city, state, zip, country);
				Store gameStore = new Store(storeCode, managerCode, storeAddress);
				gameStore.setStoreCode(storeCode);
				gameStore.setManager(managerCode);
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
	 * line, tokenizes it, and adds it to a list.
	 * 
	 * @return List<Item> itemsList
	 */
	public static List<Item> loadItemFile() {
		List<Item> itemsList = new ArrayList<>();
		File file = new File("data/Items.csv");
		Scanner scan = null;
		try {
			scan = new Scanner(file);
			scan.nextLine();
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				String token[] = line.split(",");
				String code = token[0];
				String type = token[1];
				String name = token[2];
				if (type.equals("PN")){
					double basePrice = Double.parseDouble(token[3]);
					ProductNew pr = new ProductNew(code, type, name, basePrice);
					itemsList.add(pr);
				}else if (type.equals("PU")) {
					double basePrice = Double.parseDouble(token[3]);
					ProductUsed pr = new ProductUsed(code, type, name, basePrice);
					itemsList.add(pr);
				}else if (type.equals("PG")) {
					ProductGiftCard pr = new ProductGiftCard(code, type, name, 0.0);
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

	/**
	 * This method takes in information from the "sales.csv" files, reads the file
	 * line by line, and tokenizes it, and adds it to a sale list.
	 * 
	 * @return List<Sale> saleFile
	 */

	public static List<Sale> loadSaleFile() {
		File f = new File("data/Sales.csv");
		Scanner s = null;
		List<Sale> saleList = new ArrayList<>();
		try {
			s = new Scanner(f);
			s.nextLine();
			while (s.hasNextLine()) {
				String line = s.nextLine();
				String token[] = line.split(",");
				String salesCode = token[0];
				Store store = Store.getStore(token[1], loadStoreFile());
				Person customer = Person.getPerson(token[2], loadPersonFile());
				Person salesperson = Person.getPerson(token[3], loadPersonFile());
				List<Item>itemfile = loadItemFile();
				List<Item> itemList = new ArrayList<>();
				for (int i = 4; i < token.length; i++) {
					Item item = Item.getItem(token[i], itemfile);
					if (item != null && item.getCode().equals(token[i])) {
						if (item.getType().equals("SB")) {
							String beginDate = token[i + 1];
							String endDate = token[i + 2];
							LocalDate begin = LocalDate.parse(beginDate);
							LocalDate end = LocalDate.parse(endDate);
							Subscription sub = (Subscription) item;
							sub.addBeginDate(begin);
							sub.addEndDate(end);
							itemList.add(sub);
						} else if (item.getType().equals("SV")) {
							Person employeeCode = Person.getPerson(token[i + 1], loadPersonFile());
							double numberOfHours = Double.parseDouble(token[i + 2]);
							Service ser = (Service) item;
							ser.addEmployeeCode(employeeCode);
							ser.addNumberOfHours(numberOfHours);
							itemList.add(ser);
						} else if (item.getType().equals("PU")) {
							double quantity = Double.parseDouble(token[i + 1]);
							ProductUsed pro = (ProductUsed) item;
							pro.addQuantity(quantity);
							itemList.add(pro);
						}else if (item.getType().equals("PG")) {
							double quantity = Double.parseDouble(token[i + 1]);
							ProductGiftCard pro = (ProductGiftCard) item;
							pro.addQuantity(quantity);
							itemList.add(pro);
						}else if (item.getType().equals("PN")) {
							double quantity = Double.parseDouble(token[i + 1]);
							ProductNew pro = (ProductNew) item;
							pro.addQuantity(quantity);
							itemList.add(pro);
						}
						
					}
				
				}
				Sale sale = new Sale(salesCode, customer, salesperson, store, itemList);

				saleList.add(sale);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		s.close();

		return saleList;
	}

}
