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
 *         A class that represents the Store object that's specified for the
 *         project
 */
public class Store {
	private String storeCode;
	private String manager;
	private Address storeAddress;

	public Store(String storeCode, String manager, Address storeAddress) {
		super();
		this.storeCode = storeCode;
		this.manager = manager;
		this.storeAddress = storeAddress;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Address getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(Address storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String toString() {
		return storeCode + "," + manager + "," + storeAddress;
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
//			int numOfStores = Integer.parseInt(s.nextLine());
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
//				Person manager = new Person(managerCode,);
				Store gameStore = new Store(storeCode, managerCode, storeAddress);
//				System.out.println(gameStore);
//				gameStore.setStoreCode(storeCode);
//				gameStore.setManager(managerCode);
//				gameStore.setStoreAddress(storeAddress);
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

}