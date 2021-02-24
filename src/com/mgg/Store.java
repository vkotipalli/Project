package com.mgg;

//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;

public class Store {
	private String storeCode;
	private String managerCode;
	private Address storeAddress;
	
	public Store(String storeCode, String managerCode, Address storeAddress) {
		super();
		this.storeCode = storeCode;
		this.managerCode = managerCode;
		this.storeAddress = storeAddress;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	public Address getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(Address storeAddress) {
		this.storeAddress = storeAddress;
	}

	@Override
	public String toString() {
		return  storeCode + " " + managerCode + " " + storeAddress;
	}
	
//	public List<Store> loadStoreFile() {
//		File storesInput = new File("data/Stores.csv");
//		List<Store> storesList = new ArrayList<>();
//		Scanner s = null;
//		try {
//			s = new Scanner(storesInput);
//			int numOfStores = Integer.parseInt(s.nextLine());
//			System.out.println(numOfStores);
//			while(s.hasNextLine()) {
//				String line = s.nextLine();
//				String token[] = line.split(",");
//				String storeCode = token[0];
//				String managerCode = token[1];
//				String street = token[2];
//				String city = token[3];
//				String state = token[4];
//				String zip = token[5];
//				String country = token[6];
//				
//				Address storeAddress = new Address(street,city,state,zip,country);
//				Store gameStore = new Store(storeCode,managerCode,storeAddress);
//				
//				gameStore.setStoreCode(storeCode);
//				gameStore.setManagerCode(managerCode);
//				gameStore.setStoreAddress(storeAddress);
//				storesList.add(gameStore);
//				gameStore.toString();
//				System.out.println(gameStore);
//				}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//
//		}
//		
//		return storesList;
//	}
//	
//	

}