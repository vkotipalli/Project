package com.mgg;

/**
 * @author Kotipalli, Vasavi
 * @author Maloney, Madison 
 * Date: 2/25/21
 * 
 * A class that represents the Store object that's specified for the project
 */
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

	public String toString() {
		return  storeCode + " " + managerCode + " " + storeAddress;
	}
}