package com.mgg;

public class Store {
	private String storeCode;
	private Person managerCode;
	private Address storeAddress;
	
	public Store(String storeCode, Person managerCode, Address storeAddress) {
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

	public Person getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(Person managerCode) {
		this.managerCode = managerCode;
	}

	public Address getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(Address storeAddress) {
		this.storeAddress = storeAddress;
	}

}
