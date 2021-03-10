package com.mgg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sale {
	private String code;
	private Person customer;
	private Store store;
	private List<Item> item;
	private int numOfItems;
		
	public Sale(Person customer, Store store, List<Item> item) {
		super();
		this.customer = customer;
		this.store = store;
		this.item = item;
	}
	
	public String getcode() {
		return code;
	}
	public Person getCustomer() {
		return customer;
	}
	public void setCustomer(Person customer) {
		this.customer = customer;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public List<Item> getItem() {
		return item;
	}
	public void setItem(List<Item> item) {
		this.item = item;
	}
	public int getnumOfItems() {
		return numOfItems;
	}
	public static List<Sale> loadSaleFile() {
		File f = new File("data/Sales.csv");
		Scanner s = null;
		List<Sale> saleList = new ArrayList<>();
		try {
			s = new Scanner(f);
			while(s.hasNextLine()) {
				String line = s.nextLine();
				String token[] = line.split(",");
				String salesCode = token[0];
				String storeCode = token[1];
				String customerCode = token[2];
				String salesPersonCode = token[3];
				String itemCode = token[4];
				if(token.length == 5) {
					int numOfItems = Integer.parseInt(token[5]);
				}
				else if(token.length == 6) {
					
				}
				
				
			}
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		s.close();
		return saleList;
	}
}
