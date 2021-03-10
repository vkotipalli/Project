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
 *         An abstract class that represents the Item object that's specified
 *         for the project
 */
public abstract class Item {
	private String code;
	private String name;
	private String type;

	public Item(String code, String name, String type) {
		this.code = code;
		this.name = name;
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	//Gets a item and its attributes given a specific item code. Returns null if no list is found.
	public static Item getItem(String itemCode, List<Item> itemList) {
		for(int i = 0; i<itemList.size(); i++) {
			if(itemList.get(i).getCode().equals(itemCode)) {
				return itemList.get(i);
			}
		}
		return null;
	}
}