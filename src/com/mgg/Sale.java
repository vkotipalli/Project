package com.mgg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Sale {
	private String code;
	private Person customer;
	private Store store;
	private Person salesperson;
	private List<Item> item;

		
	public Sale(String code, Person customer, Person salesperson, Store store, List<Item> item) {
		this.code = code;
		this.customer = customer;
		this.salesperson = salesperson;
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
				Store store = Store.getStore(token[1],Store.loadStoreFile());
				Person customer = Person.getPerson(token[2], Person.loadPersonFile());
				Person salesperson = Person.getPerson(token[2], Person.loadPersonFile());
				Item itemCode = Item.getItem(token[4], Item.loadItemFile());
				List<Item>itemList = new ArrayList<>();
				for(int i =4; i<token.length; i++) {
					Item item = Item.getItem(token[i], Item.loadItemFile());
					if(item.getCode().equals(token[i])) { 
						if(item.getType().equals("SV")) {
							String beginDate = token[i+1];
							String endDate = token[i+2];
							LocalDate begin = LocalDate.parse(beginDate);
							LocalDate end = LocalDate.parse(endDate);
							Subscription sub = (Subscription)item;
							sub.addBeingDate(begin);
							sub.addEndDate(end);
							itemList.add(sub);
							
							//Do if statements for Services and Products.
						}
					}
				}
				Sale sale = new Sale( salesCode, customer, salesperson,  store, itemList);
				saleList.add(sale);
				
			}
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		s.close();
		return saleList;
	}
}
