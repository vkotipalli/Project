package com.mgg;

import java.util.List;

/**
 * @author Kotipalli, Vasavi
 * @author Maloney, Madison Date: 2/25/21
 * 
 *         A class that represents the Store object that's specified for the
 *         project
 */
public class Store {
	private String storeCode;
	private Person manager;
	private Address storeAddress;

	public Store(String storeCode, Person manager, Address storeAddress) {
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

	public Person getManager() {
		return manager;
	}

	public void setManager(Person manager) {
		this.manager = manager;
	}

	public Address getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(Address storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String toString() {
		return storeCode + "," + manager.getPersonCode() + "," + storeAddress;
	}

	/**
	 * This method gets a specific instance of a store (and its attributes) given a
	 * specific store code. It returns null if no store codes matches the store code
	 * from the store list.
	 * 
	 * @param storeCode
	 * @param storeList
	 * @return specific store instance or null
	 */
	public static Store getStore(String storeCode, List<Store> storeList) {
		for (int i = 0; i < storeList.size(); i++) {
			if (storeList.get(i).getStoreCode().equals(storeCode)) {
				return storeList.get(i);
			}
		}
		return null;
	}

}