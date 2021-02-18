package unl.cse;

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
	
	
	
}
